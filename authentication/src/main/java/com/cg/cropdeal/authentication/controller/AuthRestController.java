package com.cg.cropdeal.authentication.controller;

import com.cg.cropdeal.authentication.model.AccountRequestModel;
import com.cg.cropdeal.authentication.model.MyResponseModel;
import com.cg.cropdeal.authentication.model.MyUserDetailsModel;
import com.cg.cropdeal.authentication.security.MyAuthenticationManager;
import com.cg.cropdeal.authentication.security.jwt.JwtUtil;
import com.cg.cropdeal.authentication.service.AccountServiceImpl;
import com.cg.cropdeal.authentication.service.EmailServiceImpl;
import com.cg.cropdeal.authentication.service.MyUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// rest controller class for authentication microservice
@RestController
public class AuthRestController {
	
	@Autowired
	MyAuthenticationManager myAuthenticationManager;
	@Autowired
	private AccountServiceImpl acService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	// sign up with email, password and full_name route
	@Operation(summary = "Sign up using email", description = "User can sign up using an email, password, and full name" +
		". On successful sign in, this route returns a jwt token.",
		tags = {"Sign up"})
	@PostMapping("/signup")
	public ResponseEntity<MyResponseModel> signUpWithEmail(@RequestBody AccountRequestModel req) {
		MyUserDetailsModel userDetails = acService.signUpWithEmail(req);
		
		final String jwt_token = jwtUtil.generateToken(userDetails);
		
		emailService.welcomeMail(userDetails.getUsername(), "Anmol");
		
		return new ResponseEntity<>(new MyResponseModel(jwt_token), HttpStatus.OK);
	}
	
	// sign in with email and password route
	@Operation(summary = "Sign in using email", description = "User can sign in using an email and password. On " +
		"successful sign in, this route returns a jwt token.",
		tags = {"Sign in"})
	@PostMapping("/signin")
	public ResponseEntity<MyResponseModel> signInWithEmail(@RequestBody AccountRequestModel req) {
		myAuthenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
		);
		
		MyUserDetailsModel userDetails = (MyUserDetailsModel) myUserDetailsService.loadUserByUsername(req.getEmail());
		
		final String jwt_token = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(new MyResponseModel(jwt_token), HttpStatus.OK);
	}
	
	@Operation(summary = "Test", description = "This route returns 'Hello User' and is meant for testing purpose. This " +
		"route will be removed in production.",
		tags = {"Test route"})
	@GetMapping("/test")
	public String testRoute() {
		
		return "Hello User";
	}
}
