package com.cg.cropdeal.authentication.controller;

import com.cg.cropdeal.authentication.model.AccountRequestModel;
import com.cg.cropdeal.authentication.model.MyResponseModel;
import com.cg.cropdeal.authentication.model.MyUserDetailsModel;
import com.cg.cropdeal.authentication.security.MyAuthenticationManager;
import com.cg.cropdeal.authentication.security.jwt.JwtUtil;
import com.cg.cropdeal.authentication.service.AccountServiceImpl;
import com.cg.cropdeal.authentication.service.MyUserDetailsService;
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
	
	// sign up with email, password and full_name route
	@PostMapping("/signup")
	public ResponseEntity<MyResponseModel> signUpWithEmail(@RequestBody AccountRequestModel req) {
		MyUserDetailsModel userDetails = acService.signUpWithEmail(req);
		
		final String jwt_token = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(new MyResponseModel(jwt_token), HttpStatus.OK);
	}
	
	// sign in with email and password route
	@PostMapping("/signin")
	public ResponseEntity<MyResponseModel> signInWithEmail(@RequestBody AccountRequestModel req) {
		myAuthenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
		);
		
		MyUserDetailsModel userDetails = (MyUserDetailsModel) myUserDetailsService.loadUserByUsername(req.getEmail());
		
		final String jwt_token = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(new MyResponseModel(jwt_token), HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String testRoute() {
		
		return "Hello User";
	}
}
