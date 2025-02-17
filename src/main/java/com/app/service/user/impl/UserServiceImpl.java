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
		int result = userDAO.saveUser(user);
		return result;
	}

	@Override
	public List<User> findUserList() {
		List<User> userList = userDAO.findUserList();
		System.out.println(userList);
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
		int result = userDAO.modifyUser(user);
		return result;
	}

	@Override
	public boolean isEmailCheck(String email) {
		// 이메일 중복 체크
		boolean isDuplicate = userDAO.isEmailCheck(email);
		System.out.println("이메일 중복 여부: " + isDuplicate);
		return isDuplicate;
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
		int result = userDAO.changeUserPassword(user);
		return result;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userDAO.findUserByEmail(email);
		return user;
	}

	/** (추가) 사용자 삭제 기능 */
	@Override
	public int deleteUser(String email) {
		int result = userDAO.deleteUser(email);
		System.out.println("사용자 삭제 처리 결과: " + result);
		return result;
	}

	/** (추가) 사용자 역할 변경 기능 */
	@Override
	public int updateUserRole(String email, String newRole) {
		int result = userDAO.updateUserRole(email, newRole);
		System.out.println("사용자 역할 변경 처리 결과: " + result);
		return result;
	}
}
