package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.dao.IAccountRepository;
import com.cg.cropdeal.authentication.exception.InvalidCredentialsException;
import com.cg.cropdeal.authentication.exception.InvalidPasswordException;
import com.cg.cropdeal.authentication.exception.UserAlreadyExistsException;
import com.cg.cropdeal.authentication.exception.UserNotFoundException;
import com.cg.cropdeal.authentication.model.Account;
import com.cg.cropdeal.authentication.model.MyRequestModel;
import com.cg.cropdeal.authentication.model.MyUserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements UserDetailsService, IAccountService {
	
	private final IAccountRepository repository;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	private final EmailServiceImpl emailService;
	
	@Autowired
	public AccountServiceImpl(IAccountRepository repository, BCryptPasswordEncoder passwordEncoder,
	                          EmailServiceImpl emailService) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = repository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found: " + username);
		}
		return new MyUserDetailsModel(user);
	}
	
	
	@Override
	public MyUserDetailsModel signUpWithEmail(MyRequestModel ac) {
		if (Objects.isNull(ac.getEmail()) || ac.getEmail().isBlank()) {
			throw new InvalidCredentialsException("Email cannot be empty.");
		}
		if (Objects.isNull(ac.getPassword()) || ac.getPassword().isBlank()) {
			throw new InvalidCredentialsException("Password cannot be empty.");
		}
		if (Objects.isNull(ac.getFullName()) || ac.getFullName().isBlank()) {
			throw new InvalidCredentialsException("Name field cannot be empty.");
		}
		
		// check if account object already exist in database
		Account dataFromDb = repository.findByUserName(ac.getEmail());
		if (Objects.isNull(dataFromDb)) {
			// if account object doesn't already exist in database
			// save the account object into database
			String encryptedPwd = passwordEncoder.encode(ac.getPassword());
			ac.setPassword(encryptedPwd);
			Account account = new Account(ac);
			repository.save(account);
			return new MyUserDetailsModel(account);
		}
		// if account object already exist in database throw exception
		throw new UserAlreadyExistsException("User account already exists.");
	}
	
	@Override
	public MyUserDetailsModel signInWithEmail(Account ac) {
		// check for empty values in account object
		if (Objects.isNull(ac.getUserName()) || ac.getUserName().isBlank()) {
			throw new InvalidCredentialsException("Username cannot be empty.");
		}
		if (Objects.isNull(ac.getPassword()) || ac.getPassword().isBlank()) {
			throw new InvalidCredentialsException("Password cannot be empty.");
		}
		// check if account object exist in database
		Account acFromDb = repository.findByUserName(ac.getUserName());
		if (!Objects.isNull(acFromDb)) {
			// check if password is correct
			if (ac.getPassword().equals(acFromDb.getPassword())) {
				ac = acFromDb;
				ac.setPassword(null);
				return new MyUserDetailsModel(ac);
			}
			// throw exception if password is wrong
			throw new InvalidPasswordException("Invalid password.");
		}
		// throw exception if account object is not in database
		throw new UserNotFoundException("User does not exist.");
	}
	
	@Override
	public String resetPassword(String email) {
		if (email == null || email.isBlank()) throw new InvalidCredentialsException("Email cannot be empty.");
		
		Account user = repository.findByUserName(email);
		
		if (user == null) throw new UserNotFoundException("User doesn't exist.");
		
		emailService.welcomeMail(user.getUserName(), user.getFullName());
		
		return "Success";
	}
}
