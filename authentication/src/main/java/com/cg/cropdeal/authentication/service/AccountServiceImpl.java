package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.dao.IAccountRepository;
import com.cg.cropdeal.authentication.exception.InvalidCredentialsException;
import com.cg.cropdeal.authentication.exception.InvalidPasswordException;
import com.cg.cropdeal.authentication.exception.UserAlreadyExistsException;
import com.cg.cropdeal.authentication.exception.UserNotFoundException;
import com.cg.cropdeal.authentication.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	IAccountRepository acRepo;

	@Override
	public Account signUpWithEmail (Account ac) {

		if (Objects.isNull(ac.getEmail()) || ac.getEmail().isBlank()) {
			throw new InvalidCredentialsException("Email cannot be empty.");
		}
		if (Objects.isNull(ac.getPassword()) || ac.getPassword().isBlank()) {
			throw new InvalidCredentialsException("Password cannot be empty.");
		}
		if (Objects.isNull(ac.getFullName()) || ac.getFullName().isBlank()) {
			throw new InvalidCredentialsException("Name field cannot be empty.");
		}

		if (acRepo.findByEmail(ac.getEmail()) == null) {
			acRepo.save(ac);
			ac.setPassword(null);
			return ac;
		}
		throw new UserAlreadyExistsException("User account already exists.");
	}

	@Override
	public Account signInWithEmail (Account ac) {
		if (Objects.isNull(ac.getEmail()) || ac.getEmail().isBlank()) {
			throw new InvalidCredentialsException("Username cannot be empty.");
		}
		if (Objects.isNull(ac.getPassword()) || ac.getPassword().isBlank()) {
			throw new InvalidCredentialsException("Password cannot be empty.");
		}
		Account acFromDb = acRepo.findByEmail(ac.getEmail());
		if (acFromDb != null) {
			if (ac.getEmail().equals(acFromDb.getEmail())) {
				if (ac.getPassword().equals(acFromDb.getPassword())) {
					ac = acFromDb;
					ac.setPassword(null);
					return ac;
				}
				throw new InvalidPasswordException("Invalid password.");
			}
		}
		throw new UserNotFoundException("User does not exist.");
	}
}
