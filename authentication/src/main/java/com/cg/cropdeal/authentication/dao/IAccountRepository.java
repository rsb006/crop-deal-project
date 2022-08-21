package com.cg.cropdeal.authentication.dao;

import com.cg.cropdeal.authentication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
	Account findByEmail (String email);
}
