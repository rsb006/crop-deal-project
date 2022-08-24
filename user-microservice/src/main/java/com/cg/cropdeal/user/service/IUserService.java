package com.cg.cropdeal.user.service;

import com.cg.cropdeal.user.model.User;

public interface IUserService {
	
     String addUser(User user);
     String deleteUser(Long userId);
     User getUser(Long userId);
     User updateUser(Long userId,User user);
     
     
}
