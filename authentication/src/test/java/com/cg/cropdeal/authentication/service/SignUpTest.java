package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.dao.IAccountRepository;
import com.cg.cropdeal.authentication.exception.InvalidCredentialsException;
import com.cg.cropdeal.authentication.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SignUpTest {
	Account account;
	@InjectMocks
	AccountServiceImpl serviceClass = new AccountServiceImpl();

	@Mock
	IAccountRepository accountRepo;

	@BeforeEach
	void setUp () {
		account = new Account();
	}

	//	testing InvalidCredentialsException.class
	@Test
	@DisplayName ("Test exception for empty email")
	void testEmptyEmail () {
		account.setPassword("test");
		account.setFullName("test");
		Assertions.assertThrows(InvalidCredentialsException.class,
		 () -> serviceClass.signUpWithEmail(account), "should throw invalid " +
			"credentials exception");
	}

	@Test
	@DisplayName ("Test exception for empty password")
	void testEmptyPassword () {
		account.setEmail("test");
		account.setFullName("test");
		Assertions.assertThrows(InvalidCredentialsException.class,
		 () -> serviceClass.signUpWithEmail(account), "should throw invalid " +
			"credentials exception");
	}

	@Test
	@DisplayName ("Test exception for empty fullName")
	void testEmptyFullName () {
		account.setEmail("test");
		account.setPassword("test");
		Assertions.assertThrows(InvalidCredentialsException.class,
		 () -> serviceClass.signUpWithEmail(account), "should throw invalid " +
			"credentials exception");
	}


}
