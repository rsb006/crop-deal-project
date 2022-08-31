package com.cg.cropdeal.authentication.security;

import com.cg.cropdeal.authentication.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationManager implements AuthenticationManager {
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//  check whether credentials are correct
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authentication.getName());
		
		if (passwordEncoder().matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),
				userDetails.getAuthorities());
		}
		
		throw new BadCredentialsException("Invalid credentials");
	}
}
