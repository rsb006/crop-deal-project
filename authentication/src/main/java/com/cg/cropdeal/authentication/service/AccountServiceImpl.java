package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.dao.IAccountRepository;
import com.cg.cropdeal.authentication.exception.InvalidCredentialsException;
import com.cg.cropdeal.authentication.exception.InvalidPasswordException;
import com.cg.cropdeal.authentication.exception.UserAlreadyExistsException;
import com.cg.cropdeal.authentication.exception.UserNotFoundException;
import com.cg.cropdeal.authentication.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	IAccountRepository acRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	// sign up user with email, password and full_name
	@Override
	public Account signUpWithEmail (Account ac) {
		// check for empty values in account object
		if (Objects.isNull(ac.getUserName()) || ac.getUserName().isBlank()) {
			throw new InvalidCredentialsException("Email cannot be empty.");
		}
		if (Objects.isNull(ac.getPassword()) || ac.getPassword().isBlank()) {
			throw new InvalidCredentialsException("Password cannot be empty.");
		}
		if (Objects.isNull(ac.getFullName()) || ac.getFullName().isBlank()) {
			throw new InvalidCredentialsException("Name field cannot be empty.");
		}

		// check if account object already exist in database
		Account dataFromDb = acRepo.findByUserName(ac.getUserName());
		if (Objects.isNull(dataFromDb)) {
			// if account object doesn't already exist in database
			// save the account object into database
			String encryptedPwd = encoder.encode(ac.getPassword());
			ac.setPassword(encryptedPwd);
			acRepo.save(ac);
			ac.setPassword(null);
			return ac;
		}
		// if account object already exist in database throw exception
		throw new UserAlreadyExistsException("User account already exists.");
	}

	// sign in with email and password functionality
	@Override
	public Account signInWithEmail (Account ac) {
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
}
