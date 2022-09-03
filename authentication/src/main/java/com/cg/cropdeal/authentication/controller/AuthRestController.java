package com.cg.cropdeal.authentication.controller;

import com.cg.cropdeal.authentication.model.MyRequestModel;
import com.cg.cropdeal.authentication.model.MyResponseModel;
import com.cg.cropdeal.authentication.model.MyUserDetailsModel;
import com.cg.cropdeal.authentication.security.MyAuthenticationManager;
import com.cg.cropdeal.authentication.security.jwt.JwtUtil;
import com.cg.cropdeal.authentication.service.AccountServiceImpl;
import com.cg.cropdeal.authentication.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

// rest controller class for authentication microservice
@RestController
public class AuthRestController {
	
	private final MyAuthenticationManager myAuthenticationManager;
	private final JwtUtil jwtUtil;
	private final AccountServiceImpl accountServiceImpl;
	@Autowired
	private SmsService smsService;
	
	@Autowired
	public AuthRestController(MyAuthenticationManager myAuthenticationManager, JwtUtil jwtUtil,
	                          AccountServiceImpl accountServiceImpl) {
		this.myAuthenticationManager = myAuthenticationManager;
		this.jwtUtil = jwtUtil;
		this.accountServiceImpl = accountServiceImpl;
	}
	
	
	// sign up with email, password and full_name route
	@Operation(summary = "Sign up using email", description = "User can sign up using an email, password, and full name" +
		". On successful sign in, this route returns a jwt token.",
		tags = {"Sign up"})
	@PostMapping("/signup")
	public ResponseEntity<MyResponseModel> signUpWithEmail(@RequestBody MyRequestModel req) {
		MyUserDetailsModel userDetails = accountServiceImpl.signUpWithEmail(req);
		
		final String jwt_token = jwtUtil.generateToken(userDetails);

//		emailService.welcomeMail(userDetails.getUsername(), "Anmol");
		
		return new ResponseEntity<>(new MyResponseModel(jwt_token), HttpStatus.OK);
	}
	
	// sign in with email and password route
	@Operation(summary = "Sign in using email", description = "User can sign in using an email and password. On " +
		"successful sign in, this route returns a jwt token.",
		tags = {"Sign in"})
	@PostMapping("/signin")
	public ResponseEntity<MyResponseModel> signInWithEmail(@RequestBody MyRequestModel req) {
		myAuthenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
		);
		
		MyUserDetailsModel userDetails = (MyUserDetailsModel) accountServiceImpl.loadUserByUsername(req.getEmail());
		
		final String jwt_token = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(new MyResponseModel(jwt_token), HttpStatus.OK);
	}
	
	@Operation(summary = "Reset password", description = "This route is for password reset",
		tags = {"Reset password"})
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam String email) {
		
		return ResponseEntity.ok(accountServiceImpl.resetPassword(email));
	}
	
	@Operation(summary = "Test", description = "This route returns 'Hello User' and is meant for testing purpose. This " +
		"route will be removed in production.",
		tags = {"Test route"})
	@GetMapping("/test")
	public String testRoute() {
		smsService.sendSms("+918601297319");
		return "Hello User";
	}
}
