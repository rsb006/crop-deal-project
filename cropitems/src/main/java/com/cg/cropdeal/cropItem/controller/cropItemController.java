package com.cg.cropdeal.cropItem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.cg.cropdeal.cropItem.model.cropItem;
import com.cg.cropdeal.cropItem.service.cropItemService;

//For Accessing the url
@RestController
@RequestMapping("/cropItem")    //top level mapping
public class cropItemController {
	
	
	@Autowired
	private cropItemService cropitemService;
    
	@GetMapping("/viewcropitem/{cropid}")  //retrieve data from server,low level mapping-cropid
	public ResponseEntity<cropItem>getcropItem(@PathVariable String cropid){ //response entity is used for return the response,return user
		return new ResponseEntity<>(cropitemService.cropitemGetServ(cropid), HttpStatus.OK);
	}
	@PostMapping("/addcropitem")  //add crop
	public ResponseEntity<cropItem>savecropItem(@RequestBody cropItem cropitem){ //RequestBody is used for passing request
		return new ResponseEntity<>(cropitemService.cropitemAddServ(cropitem), HttpStatus.OK);
	}
	
	@DeleteMapping("/removecropitem/{cropid}")//remove cropitem
	public ResponseEntity<String>removecropItem(@PathVariable String cropid){
		return new ResponseEntity<>(cropitemService.cropitemRemoveServ(cropid),HttpStatus.OK);
	}
	
	@PutMapping("/updatecropitem/{cropid}")//update based on the cropid
	public ResponseEntity<cropItem>modifycropItem(@PathVariable String cropid, @RequestBody cropItem cropitem){
		return new ResponseEntity<>(cropitemService.cropitemUpdateServ(cropid, cropitem), HttpStatus.OK);
	}
}