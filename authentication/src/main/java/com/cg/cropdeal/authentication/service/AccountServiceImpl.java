package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.dao.IAccountRepository;
import com.cg.cropdeal.authentication.exception.InvalidCredentialsException;
import com.cg.cropdeal.authentication.exception.InvalidPasswordException;
import com.cg.cropdeal.authentication.exception.UserAlreadyExistsException;
import com.cg.cropdeal.authentication.exception.UserNotFoundException;
import com.cg.cropdeal.authentication.model.Account;
import com.cg.cropdeal.authentication.model.AccountRequestModel;
import com.cg.cropdeal.authentication.model.MyUserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl {
	
	@Autowired
	IAccountRepository acRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	// sign up user with email, password and full_name
	
	public MyUserDetailsModel signUpWithEmail(AccountRequestModel req) {
		// check for empty values in account object
		if (Objects.isNull(req.getEmail()) || req.getEmail().isBlank()) {
			throw new InvalidCredentialsException("Email cannot be empty.");
		}
		if (Objects.isNull(req.getPassword()) || req.getPassword().isBlank()) {
			throw new InvalidCredentialsException("Password cannot be empty.");
		}
		if (Objects.isNull(req.getFullName()) || req.getFullName().isBlank()) {
			throw new InvalidCredentialsException("Name field cannot be empty.");
		}
		
		// check if account object already exist in database
		Account dataFromDb = acRepo.findByUserName(req.getEmail());
		if (Objects.isNull(dataFromDb)) {
			// if account object doesn't already exist in database
			// save the account object into database
			String encryptedPwd = encoder.encode(req.getPassword());
			req.setPassword(encryptedPwd);
			Account account = new Account(req);
			acRepo.save(account);
			return new MyUserDetailsModel(account);
		}
		// if account object already exist in database throw exception
		throw new UserAlreadyExistsException("User account already exists.");
	}
	
	// sign in with email and password functionality
	
	public Account signInWithEmail(Account ac) {
		// check for empty values in account object
		if (Objects.isNull(ac.getUserName()) || ac.getUserName().isBlank()) {
			throw new InvalidCredentialsException("Username cannot be empty.");
		}
		if (Objects.isNull(ac.getPassword()) || ac.getPassword().isBlank()) {
			throw new InvalidCredentialsException("Password cannot be empty.");
		}
		// check if account object exist in database
		Account acFromDb = acRepo.findByUserName(ac.getUserName());
		if (!Objects.isNull(acFromDb)) {
			// check if password is correct
			if (ac.getPassword().equals(acFromDb.getPassword())) {
				ac = acFromDb;
				ac.setPassword(null);
				return ac;
			}
			// throw exception if password is wrong
			throw new InvalidPasswordException("Invalid password.");
		}
		// throw exception if account object is not in database
		throw new UserNotFoundException("User does not exist.");
	}
	
	public String resetPassword(String email) {
		if (email == null || email.isBlank()) throw new InvalidCredentialsException("Email cannot be empty.");
		
		Account user = acRepo.findByUserName(email);
		
		if (user == null) throw new UserNotFoundException("User doesn't exist.");
		
		emailService.welcomeMail(user.getUserName(), user.getFullName());
		
		return "Success";
	}
}
