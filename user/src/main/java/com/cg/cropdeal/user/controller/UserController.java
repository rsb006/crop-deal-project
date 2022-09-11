package com.cg.cropdeal.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cropdeal.user.dto.UserDto;
import com.cg.cropdeal.user.model.User;
import com.cg.cropdeal.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UserController {
	
	@Autowired
	private UserService userService; 

	@PostMapping ("/add-user")
	@ApiOperation(value="Add user details",
	notes = "enter all the required and valid user details to add the user in database",
	response = String.class)
	public ResponseEntity<String> addUser (@RequestBody UserDto userDto) {

		return new ResponseEntity<>(userService.addUser(userDto),
		 HttpStatus.OK);
	} 

	@DeleteMapping("/delete-user/{userId}")
	@ApiOperation(value="Delete user details by id",
	notes = "enter valid user id to be deleted from the  database",
	response = String.class)
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		
		return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.OK);	
		
	}
	
	@GetMapping("/get-user/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		
		return new ResponseEntity<>(userService.getUser(userId),
				 HttpStatus.OK);
	}
	
	@GetMapping("/get-user-username/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
		
		return new ResponseEntity<>(userService.getUserByUserName(userName),
				 HttpStatus.OK);
	}
	
	//get all the users from database
	
	@GetMapping("/get-all-users")
	public ResponseEntity<List<User>> getAllUsers() {
		
		return new ResponseEntity<>(userService.getAllUsers(),
				 HttpStatus.OK);
	}
	
	
	@PutMapping("/update-user/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
		
		return new ResponseEntity<>(userService.updateUser(userId, user),
				 HttpStatus.OK);
	}
	
	@PutMapping("/update-user-status/{userId}/{status}")
	public ResponseEntity<String> updateUserStatus(@PathVariable Long userId, @PathVariable String status) {
		
		return new ResponseEntity<>(userService.markUserStatus(userId, status),
				 HttpStatus.OK);
	}
}
