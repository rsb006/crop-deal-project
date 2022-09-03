package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.model.Account;
import com.cg.cropdeal.authentication.model.MyRequestModel;
import com.cg.cropdeal.authentication.model.MyUserDetailsModel;

public interface IAccountService {
	// sign up user using email, password and fullname
	MyUserDetailsModel signUpWithEmail(MyRequestModel ac);
	
	// sign in user using email, password
	MyUserDetailsModel signInWithEmail(Account ac);
	
	String resetPassword(String email);
	
}
