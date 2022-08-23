package com.cg.cropdeal.authentication.controller;

import com.cg.cropdeal.authentication.model.Account;
import com.cg.cropdeal.authentication.model.AccountRequestModel;
import com.cg.cropdeal.authentication.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// rest controller class for authentication microservice
@RestController
public class AuthRestController {

	@Autowired
	private AccountServiceImpl acService;

	// sign up with email, password and full_name route
	@PostMapping ("/signup")
	public ResponseEntity<Account> signUpWithEmail (@RequestBody AccountRequestModel req) {

		return new ResponseEntity<>(acService.signUpWithEmail(new Account(req)),
		 HttpStatus.OK);
	}

	// sign in with email and password route
	@PostMapping ("/signin")
	public ResponseEntity<Account> signInWithEmail (@RequestBody AccountRequestModel req) {

		return new ResponseEntity<>(acService.signInWithEmail(new Account(req)),
		 HttpStatus.OK);
	}
}
