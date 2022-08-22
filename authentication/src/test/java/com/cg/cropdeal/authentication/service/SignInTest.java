package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.exception.InvalidCredentialsException;
import com.cg.cropdeal.authentication.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class SignInTest {
	@InjectMocks
	AccountServiceImpl serv = new AccountServiceImpl();

	Account ac;

	@BeforeEach
	void setUp () {
		ac = new Account();
	}

	@Test
	@DisplayName ("Test empty email")
	void testEmptyEmail () {
		ac.setPassword("test");
		Assertions.assertThrows(InvalidCredentialsException.class, () -> serv.signInWithEmail(ac), "should throw invalid credentials exception");
	}

	@Test
	@DisplayName ("Test empty password")
	void testEmptyPassword () {
		ac.setEmail("test");
		Assertions.assertThrows(InvalidCredentialsException.class, () -> serv.signInWithEmail(ac), "should throw invalid credentials exception");
	}

}

