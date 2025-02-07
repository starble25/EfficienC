package com.app.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.user.User;

@Service
public interface UserService {
	// Create
	int saveUser(User user);
	
	// Read
	List<User> findUserList();
	User findUserById(String id);
	
	// Update
	int modifyUser(User user);
}
