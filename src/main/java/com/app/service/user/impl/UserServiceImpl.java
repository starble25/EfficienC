package com.app.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.user.UserDAO;
import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;
import com.app.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> findUserList() {
		System.out.println("userServiceImpl findUserList");
		List<User> userList = userDAO.findUserList();
		return userList;
	}

	@Override
	public User checkUserAuth(User user) {
		
		User checkUser = userDAO.checkUserAuth(user);
		System.out.println(checkUser + "UserService");
		return checkUser;
	}

	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmailCheck(String getEmail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User checkUserLogin(User user) {
		User loginUser = userDAO.checkUserLogin(user);
		return loginUser;
	}

	@Override
	public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition) {

		List<User> userList = userDAO.findUserListBySearchCondition(userSearchCondition);
		
		return userList;
	}

	@Override
	public int changeUserPassword(User user) {
		int result =userDAO.changeUserPassword(user);
		return result;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userDAO.findUserByEmail(email);
		return user;
	}


}