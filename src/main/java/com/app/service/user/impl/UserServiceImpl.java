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
    private UserDAO userDAO;

    /** 📌 회원가입 (사용자 저장) */
    @Override
    public int saveUser(User user) {
        return userDAO.saveUser(user);
    }

    /** 📌 전체 사용자 조회 */
    @Override
    public List<User> findUserList() {
        System.out.println("UserServiceImpl - findUserList 호출됨");
        return userDAO.findUserList();
    }

    /** 📌 ID 기반 사용자 조회 */
    @Override
    public User findUserById(String id) {
        return userDAO.findUserById(id);
    }

    /** 📌 사용자 정보 수정 */
    @Override
    public int modifyUser(User user) {
        return userDAO.modifyUser(user);
    }

    /** 📌 이메일 중복 체크 */
    @Override
    public boolean isEmailCheck(String getEmail) {
        return userDAO.isEmailCheck(getEmail);
    }

    /** 📌 로그인 처리 */
    @Override
    public User checkUserLogin(User user) {
        return userDAO.checkUserLogin(user);
    }

    /** 📌 검색 조건에 따른 사용자 리스트 조회 */
    @Override
    public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition) {
        return userDAO.findUserListBySearchCondition(userSearchCondition);
    }

    /** 📌 이메일 기반 사용자 조회 */
    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
