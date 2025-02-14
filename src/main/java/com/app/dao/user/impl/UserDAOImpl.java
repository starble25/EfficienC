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

    // 📌 사용자 저장 (회원가입)
    @Override
    public int saveUser(User user) {
        return sqlSessionTemplate.insert("user_mapper.saveUser", user);
    }

    // 📌 전체 사용자 조회
    @Override
    public List<User> findUserList() {
        System.out.println("userDAO findUserList");
        return sqlSessionTemplate.selectList("user_mapper.findUserList");
    }

    // 📌 ID 기반 사용자 조회
    @Override
    public User findUserById(String id) {
        return sqlSessionTemplate.selectOne("user_mapper.findUserById", id);
    }

    // 📌 이메일 기반 사용자 조회 (로그인 시 사용)
    @Override
    public User findByEmail(String email) {
        return sqlSessionTemplate.selectOne("user_mapper.findByEmail", email);
    }

    // 📌 사용자 정보 수정
    @Override
    public int modifyUser(User user) {
        return sqlSessionTemplate.update("user_mapper.modifyUser", user);
    }

    // 📌 로그인 시 사용자 확인
    @Override
    public User checkUserLogin(User user) {
        return sqlSessionTemplate.selectOne("user_mapper.checkUserLogin", user);
    }

    // 📌 이메일 중복 확인
    @Override
    public boolean isEmailCheck(String email) {
        Integer count = sqlSessionTemplate.selectOne("user_mapper.isEmailCheck", email);
        return count != null && count > 0;
    }

    // 📌 검색 조건에 따른 사용자 리스트 조회
    @Override
    public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition) {
        return sqlSessionTemplate.selectList("user_mapper.findUserListBySearchCondition", userSearchCondition);
    }

	@Override
	public User checkUserAuth(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int changeUserPassword(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
