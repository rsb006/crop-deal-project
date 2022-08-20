package com.cg.cropdeal.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

	@GetMapping("/home")
	public String helloWorld() {
		return "Hello World 1";
	}
}
