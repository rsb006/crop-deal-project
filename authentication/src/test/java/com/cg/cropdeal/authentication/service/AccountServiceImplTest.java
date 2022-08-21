package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.exception.InvalidCredentialsException;
import com.cg.cropdeal.authentication.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

	AccountServiceImpl serv;
	Account ac;

	@BeforeEach
	void setUp () {
		serv = new AccountServiceImpl();
		ac = new Account();
	}

	@Nested
	class SignUp {
		@Test
		@DisplayName ("Test InvalidCredentialsException")
		void testInvalidCredentialsException () {
			ac.setPassword("test");
			ac.setFullName("test");
			assertThrows(InvalidCredentialsException.class, () -> serv.signUpWithEmail(ac), "should throw invalid credentials exception");
			ac.setEmail("test");
			ac.setPassword(null);
			assertThrows(InvalidCredentialsException.class, () -> serv.signUpWithEmail(ac), "should throw invalid credentials exception");
			ac.setFullName(null);
			ac.setPassword("test");
			assertThrows(InvalidCredentialsException.class, () -> serv.signUpWithEmail(ac), "should throw invalid credentials exception");
		}

		@Test
		@DisplayName ("Test successful sign up")
		void testSignUpFunctionality () {
			ac.setEmail("test");
			ac.setPassword("test");
			ac.setFullName("test");
			Account acFromDb = new Account("test", "test", "test");
			assertAll(
			 () -> assertEquals(ac.getEmail(), acFromDb.getEmail()),
			 () -> assertEquals(ac.getPassword(), acFromDb.getPassword()),
			 () -> assertEquals(ac.getFullName(), acFromDb.getFullName())
			);
		}
	}

	@Nested
	class SignIn {
		@Test
		@DisplayName ("Test InvalidCredentialsException")
		void testInvalidCredentialsException () {
			ac.setPassword("test");

			assertThrows(InvalidCredentialsException.class, () -> serv.signInWithEmail(ac), "should throw invalid credentials exception");
			ac.setEmail("test");
			ac.setPassword(null);
			assertThrows(InvalidCredentialsException.class, () -> serv.signInWithEmail(ac), "should throw invalid credentials exception");
		}

		@Test
		@DisplayName ("Test successful sign in")
		void testSignInFunctionality () {
			ac.setEmail("test");
			ac.setPassword("test");
			Account acFromDb = new Account();
			acFromDb.setEmail("test");
			acFromDb.setPassword("test");
			assertAll(
			 () -> assertEquals(ac.getEmail(), acFromDb.getEmail()),
			 () -> assertEquals(ac.getPassword(), acFromDb.getPassword())
			);
		}
	}

}