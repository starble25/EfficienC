package com.app.service.user;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.user.User;

import lombok.Getter;

@Service
public interface UserService {
	// Create
	int saveUser(User user);
	
	// Read
	List<User> findUserList();
	
	// Update
	int modifyUser(User user);

	boolean isEmailCheck(String getEmail);
	
	User checkUserLogin(User user);

	User checkUserAuth(User user);

	int changeUserPassword(User user);

	User findUserByEmail(String email);
}
