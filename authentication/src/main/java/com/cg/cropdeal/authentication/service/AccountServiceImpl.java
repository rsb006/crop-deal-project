package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.dao.IAccountRepository;
import com.cg.cropdeal.authentication.exception.*;
import com.cg.cropdeal.authentication.model.Account;
import com.cg.cropdeal.authentication.model.MyRequestModel;
import com.cg.cropdeal.authentication.model.MyResponseModel;
import com.cg.cropdeal.authentication.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements UserDetailsService, IAccountService {
	private final IAccountRepository repository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final EmailServiceImpl emailService;
	private final SmsService smsService;
	private final JwtUtil jwtUtil;
	
	@Autowired
	public AccountServiceImpl(IAccountRepository repository, BCryptPasswordEncoder passwordEncoder,
	                          EmailServiceImpl emailService, SmsService smsService, JwtUtil jwtUtil) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.smsService = smsService;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
		Account user = repository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("User not found with given email: " + email);
		}
		return user;
	}
	
	@Override
	public MyResponseModel signUpWithEmail(MyRequestModel req) {
		if (!req.signUpValidation()) {
			throw new InvalidCredentialsException("Field(s) cannot be empty.");
		}
		
		// check if account object already exist in database
		Account dataFromDb = repository.findByEmail(req.getEmail());
		if (Objects.isNull(dataFromDb)) {
			// if account object doesn't already exist in database
			// save the account object into database
			String encryptedPwd = passwordEncoder.encode(req.getPassword());
			req.setPassword(encryptedPwd);
			Account account = new Account(req);
			repository.save(account);
			String JWT_TOKEN = jwtUtil.generateToken(account);
			return new MyResponseModel(JWT_TOKEN);
		}
		// if account object already exist in database throw exception
		throw new UserAlreadyExistsException("User account already exists.");
	}
	
	@Override
	public MyResponseModel signInWithEmail(MyRequestModel req) {
		// check for empty values in account object
		if (!req.signInValidation()) {
			throw new InvalidCredentialsException("Email/Password field(s) cannot be empty.");
		}
		
		// check if account object exist in database
		Account account = repository.findByEmail(req.getEmail());
		if (!Objects.isNull(account)) {
			// check if password is correct
			String encryptedPwd = passwordEncoder.encode(req.getPassword());
			if (encryptedPwd.equals(account.getPassword())) {
				String JWT_TOKEN = jwtUtil.generateToken(account);
				return new MyResponseModel(JWT_TOKEN);
			}
			// throw exception if password is wrong
			throw new InvalidPasswordException("Invalid password.");
		}
		// throw exception if account object is not in database
		throw new UserNotFoundException("User does not exist.");
	}
	
	
	public Boolean validateOTP(MyRequestModel req) {
		String otp = req.getResetCode();
		if (otp != null && !otp.isBlank()) {
			Account account = repository.findByResetCode(otp);
			return account != null;
		}
		return false;
	}
	
	@Override
	public MyResponseModel resetPassword(MyRequestModel req) {
		if (!req.resetPasswordValidation()) {
			throw new InvalidCredentialsException("Password/Reset-token field(s) cannot be empty.");
		}
		
		String resetCode = req.getResetCode();
		String password = req.getPassword();
		
		Account account = repository.findByResetCode(resetCode);
		
		if (account != null && account.getResetCode().equals(resetCode)) {
			String encryptedPassword = passwordEncoder.encode(password);
			account.setPassword(encryptedPassword);
			account.setResetCode(null);
			repository.save(account);
			String JWT_TOKEN = jwtUtil.generateToken(account);
			return new MyResponseModel(JWT_TOKEN);
		}
		
		throw new InvalidSessionException("Invalid session. Please try " + "again later.");
	}
	
	public String forgotPassword(String email) {
		if (email == null || email.isBlank()) throw new InvalidCredentialsException("Email field cannot be empty.");
		
		Account account = repository.findByEmail(email);
		
		if (account == null) throw new UserNotFoundException("User with email: " + email + " not found.");
		
		// reset options means in how many ways a user can reset password.
//		using email only (resetOptions == 1) or using email and sms otp (resetOptions == 2)
		Integer resetOptions = 1;
		
		if (account.getPhoneNumber() != null) {
			resetOptions = 2;
		}
		
		final String RESPONSE = "reset-options&" + resetOptions;
		
		return RESPONSE;
	}
}
