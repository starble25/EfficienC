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
		int result = sqlSessionTemplate.insert("user_mapper.saveUser", user);
		return result;
	}

	@Override
	public List<User> findUserList() {
		List<User> userList = sqlSessionTemplate.selectList("user_mapper.findUserList");
		System.out.println(userList);
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
		int result = sqlSessionTemplate.update("user_mapper.changeUserPassword", user);
		return result;
	}
	
	@Override
	public int modifyUser(User user) {
		int result = sqlSessionTemplate.update("user_mapper.modifyUser", user);
		return result;
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

	/** (추가) 사용자 삭제 */
	@Override
	public int deleteUser(String email) {
		int result = sqlSessionTemplate.delete("user_mapper.deleteUser", email);
		System.out.println("사용자 삭제 처리 결과: " + result);
		return result;
	}

	/** (추가) 사용자 역할 변경 */
	@Override
	public int updateUserRole(String email, String newRole) {
		User user = new User();
		user.setEmail(email);
		user.setPositionCode(newRole);
		
		int result = sqlSessionTemplate.update("user_mapper.updateUserRole", user);
		System.out.println("사용자 역할 변경 처리 결과: " + result);
		return result;
	}

	/** (추가) 이메일 중복 체크 */
	@Override
	public boolean isEmailCheck(String email) {
		Integer count = sqlSessionTemplate.selectOne("user_mapper.isEmailCheck", email);
		System.out.println("이메일 중복 확인: " + (count != null && count > 0));
		return count != null && count > 0;
	}
}
