package com.cg.cropdeal.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cropdeal.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User getByUserId(Long userId);
    User getByUserName(String username);
	User getByMobileNo(long mobileNo);
	User getByEmailId(String emailId);
    
	
}
