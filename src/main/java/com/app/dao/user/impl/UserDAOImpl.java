package com.app.dao.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.user.UserDAO;
import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> findUserList() {
		System.out.println("userDAO findUserList");
		List<User> userList = sqlSessionTemplate.selectList("user_mapper.findUserList");
		
		return userList;
	}

	@Override
	public User checkUserAuth(User user) {
		User checkUser = sqlSessionTemplate.selectOne("user_mapper.checkUserAuth", user);
		System.out.println(checkUser + "UserDAO");
		return checkUser;
	}
	
	@Override
	public int changeUserPassword(User user) {
		System.out.println(user + "userDAO");
		int result = sqlSessionTemplate.update("user_mapper.changeUserPassword",user);
		return result;
	}
	
	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User checkUserLogin(User user) {
		User loginUser = sqlSessionTemplate.selectOne("user_mapper.checkUserLogin", user);

		return loginUser;
	}
	
	@Override
	public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition) {
		
		List<User> userList = sqlSessionTemplate.selectList("user_mapper.findUserListBySearchCondition", userSearchCondition);
		
		return userList;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = sqlSessionTemplate.selectOne("user_mapper.findUserByEmail", email);
		return user;
	}



}
