package com.cg.cropdeal.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.cropdeal.user.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

	Bank getByAccountNo(Long accountNo);

}
