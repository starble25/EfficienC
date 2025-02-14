package com.app.service.user.impl;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.user.UserDAO;
import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;
import com.app.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /** 📌 사용자 저장 (회원가입) */
    @Override
    public int saveUser(User user) {
        return sqlSessionTemplate.insert("user_mapper.saveUser", user);
    }

    /** 📌 전체 사용자 조회 */
    @Override
    public List<User> findUserList() {
        System.out.println("UserServiceImpl - findUserList 호출됨");
        return sqlSessionTemplate.selectList("user_mapper.findUserList");
    }

    /** 📌 ID 기반 사용자 조회 */
    @Override
    public User findUserById(String id) {
        return sqlSessionTemplate.selectOne("user_mapper.findUserById", id);
    }

    /** 📌 이메일 기반 사용자 조회 (로그인 시 사용) */
    @Override
    public User findUserByEmail(String email) {
        return sqlSessionTemplate.selectOne("user_mapper.findByEmail", email);
    }

    /** 📌 사용자 정보 수정 */
    @Override
    public int modifyUser(User user) {
        return sqlSessionTemplate.update("user_mapper.modifyUser", user);
    }

    /** 📌 로그인 시 사용자 확인 */
    @Override
    public User checkUserLogin(User user) {
        return sqlSessionTemplate.selectOne("user_mapper.checkUserLogin", user);
    }

    /** 📌 이메일 중복 확인 */
    @Override
    public boolean isEmailCheck(String email) {
        Integer count = sqlSessionTemplate.selectOne("user_mapper.isEmailCheck", email);
        return count != null && count > 0;
    }

    /** 📌 검색 조건에 따른 사용자 리스트 조회 */
    @Override
    public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition) {
        return sqlSessionTemplate.selectList("user_mapper.findUserListBySearchCondition", userSearchCondition);
    }

    /** 📌 사용자 인증 확인 (비밀번호 찾기용) */
    @Override
    public User checkUserAuth(User user) {
        return sqlSessionTemplate.selectOne("user_mapper.checkUserAuth", user);
    }

    /** 📌 비밀번호 변경 */
    @Override
    public int changeUserPassword(User user) {
        return sqlSessionTemplate.update("user_mapper.changeUserPassword", user);
    }

	@Override
	public void sendAuthCodeToEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyAuthCode(String email, int authCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int resetPassword(String email, String newPassword) {
		// TODO Auto-generated method stub
		return 0;
	}
}
