package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.model.Account;

public interface IAccountService {
	// sign up user using email, password and fullname
	Account signUpWithEmail (Account ac);

	// sign in user using email, password
	Account signInWithEmail (Account ac);

}
