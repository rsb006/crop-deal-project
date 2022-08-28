package com.cg.cropdeal.authentication;

import com.cg.cropdeal.authentication.dao.IAccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories (basePackageClasses = IAccountRepository.class)
public class AuthenticationApplication {

	public static void main (String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
