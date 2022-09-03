package com.cg.cropdeal.authentication.security;

import com.cg.cropdeal.authentication.security.filters.MyJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private MyJwtFilter myJwtFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/test")
			.hasRole("FARMER")
			.antMatchers("/reset-password")
			.hasAnyRole("FARMER", "DEALER", "ADMIN")
			.anyRequest()
			.permitAll()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(myJwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	
}
