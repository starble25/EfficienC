package com.app.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.dto.user.User;

@Repository
public interface UserDAO {
	// Create
	int saveUser(User user);
	
	// Read
	List<User> findUserList();
	User findUserById(String id);
	
	// Update
	int modifyUser(User user);
	
	public User checkUserLogin(User user);
}
