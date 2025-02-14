package com.app.dao.user;

import java.util.List;

import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;

public interface UserDAO {
	// Create
	int saveUser(User user);
	
	
	// Update
	int modifyUser(User user);
	
	User checkUserLogin(User user);
	
	public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition);

	User checkUserAuth(User user);

	int changeUserPassword(User user);

	User findUserByEmail(String email);

	List<User> findUserList();

	
}
