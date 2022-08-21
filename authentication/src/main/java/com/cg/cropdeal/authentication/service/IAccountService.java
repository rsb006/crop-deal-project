package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.model.Account;

public interface IAccountService {
	Account signUpWithEmail (Account ac);

	Account signInWithEmail (Account ac);

}
