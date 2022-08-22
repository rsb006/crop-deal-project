package com.cg.cropdeal.user.service;

import com.cg.cropdeal.user.model.User;

public interface IUserService {
	
     User AddUser(User user);
     String deleteUser(Long userId);
     User GetUser(Long userId);
     User updateUser(Long userId,User user);
     
     
}
