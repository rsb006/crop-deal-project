package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.model.Account;
import com.cg.cropdeal.authentication.model.AccountRequestModel;

public interface IAccountService {
	// sign up user using email, password and fullname
	AccountRequestModel signUpWithEmail(AccountRequestModel ac);
	
	// sign in user using email, password
	Account signInWithEmail(Account ac);
	
}
