package com.cg.cropdeal.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.meta.When;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cropdeal.user.model.User;
import com.cg.cropdeal.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UpdateUserTest.class})
class UpdateUserTest {


	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	GetUserTest getUser=new GetUserTest();

	List<User> list=new ArrayList<>();
	
	
	@Test
	void testUpdateUserById() {
	 
		var user=new User();
		user.setUserId((long)111);
		user.setUserType("farmer");
		User userdb=getUser.createUser();
		System.out.println(userdb);
		when(userRepository.getByUserId((long) 111)).thenReturn(userdb);
		User u1=userService.updateUser((long) 111, user);
		System.out.println(u1);
		assertEquals("farmer",u1.getUserType());
		
		
	}
	
	@Test
	void testUpdateUserStatus() {
		var user=getUser.createUser();
		when(userRepository.getByUserId((long) 111)).thenReturn(user);
		String actual=userService.markUserStatus((long)111, "active");
		String expected="updated user status as active";
		assertEquals(expected,actual);
		
	}
	
	@Test
	void testUpdateUserStatusGivenInvalidUserId() {
		
		when(userRepository.getByUserId((long) 111)).thenReturn(null);
		String actual=userService.markUserStatus((long) 111, "active");
		String expected="invalid user id";
		assertEquals(expected,actual);
		
	}
	
	
	

}
