package com.cg.cropdeal.user.test;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.cg.cropdeal.user.service.UserService;

@Profile("test")
@Configuration
public class UserServiceTestConfig {

	@Bean
	@Primary
	public UserService userService() {
		return Mockito.mock(UserService.class);
	}
	
	
}
