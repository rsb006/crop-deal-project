package com.cg.cropdeal.authentication.security;

import com.cg.cropdeal.authentication.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public AuthenticationManager authenticationManager (HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, MyUserDetailsService userDetailService)
	 throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
		 .userDetailsService(userDetailService)
		 .passwordEncoder(bCryptPasswordEncoder)
		 .and()
		 .build();
	}

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http.csrf().disable()
		 .authorizeHttpRequests()
		 .antMatchers("/test")
		 .hasRole("FARMER")
		 .anyRequest()
		 .permitAll()
		 .and()
		 .formLogin()
		 .permitAll();

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder encoder () {
		return new BCryptPasswordEncoder();
	}


}
