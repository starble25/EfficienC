package com.app.service.user;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;

import lombok.Getter;


@Service
public interface UserService {
	// Create
	int saveUser(User user);
	
	// Read
	List<User> findUserList();
	User findUserById(String id);
	
	// Update
	int modifyUser(User user);

	boolean isEmailCheck(String getEmail);
	
	User checkUserLogin(User user);
		
	public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition);
}
