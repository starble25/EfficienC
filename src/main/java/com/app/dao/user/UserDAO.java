package com.app.dao.user;

import java.util.List;

import com.app.dto.user.User;

public interface UserDAO {
	// Create
	int saveUser(User user);
	
	// Read
	List<User> findUserList();
	User findUserById(String id);
	
	// Update
	int modifyUser(User user);
}
