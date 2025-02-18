package com.app.dao.user;

import java.util.List;

import com.app.dto.user.User;

public interface UserDAO {
	// Create
	int saveUser(User user);
	
	
	// Update
	int modifyUser(User user);
	
	User checkUserLogin(User user);

	User checkUserAuth(User user);

	int changeUserPassword(User user);

	User findUserByEmail(String email);

	List<User> findUserList();

	
}
