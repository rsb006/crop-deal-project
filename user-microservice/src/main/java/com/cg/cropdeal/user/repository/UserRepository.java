package com.cg.cropdeal.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.cropdeal.user.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User getByUserId(Long userId);
}
