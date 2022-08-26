package com.cg.cropdeal.authentication.service;

import com.cg.cropdeal.authentication.dao.IAccountRepository;
import com.cg.cropdeal.authentication.model.Account;
import com.cg.cropdeal.authentication.model.UserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	IAccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		Account user = accountRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found: " + username);
		}
		return new UserDetailsModel(user);
	}
}
