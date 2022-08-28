package com.cg.cropdeal.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cropdeal.user.model.User;
import com.cg.cropdeal.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService; 


	@PostMapping ("/add-user")
	public ResponseEntity<String> addUser (@RequestBody User user) {

		return new ResponseEntity<>(userService.addUser(user),
		 HttpStatus.OK);
	} 

	@DeleteMapping("/delete-user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		
		return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.OK);	
		
	}
	
	@GetMapping("/get-user/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		
		return new ResponseEntity<>(userService.getUser(userId),
				 HttpStatus.OK);
	}
	
	@PutMapping("/update-user/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
		
		return new ResponseEntity<>(userService.updateUser(userId, user),
				 HttpStatus.OK);
	}
	
}
