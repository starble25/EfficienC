package com.app.dao.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.user.UserDAO;
import com.app.dto.user.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int saveUser(User user) {
		int result = sqlSessionTemplate.insert("user_mapper.saveUser", user);
		return result;
	}

	@Override
	public List<User> findUserList() {
		System.out.println("userDAO findUserList");
		List<User> userList = sqlSessionTemplate.selectList("user_mapper.findUserList");
		
		return userList;
	}

	@Override
	public User findUserById(String id) {
		// TODO Auto-generated method stub
		return null;
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

}
