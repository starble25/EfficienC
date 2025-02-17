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
    private UserDAO userDAO; // 기존 sqlSessionTemplate 대신 userDAO 사용

    /** 사용자 저장 (회원가입) */
    @Override
    public int saveUser(User user) {
        return userDAO.saveUser(user);
    }

    /** 전체 사용자 조회 */
    @Override
    public List<User> findUserList() {
        System.out.println("UserServiceImpl - findUserList 호출됨");
        return userDAO.findUserList();
    }

    /** ID 기반 사용자 조회 */
    @Override
    public User findUserById(String id) {
        return userDAO.findUserById(id);
    }

    /** 이메일 기반 사용자 조회 (로그인 시 사용) */
    @Override
    public User findUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    /** 사용자 정보 수정 */
    @Override
    public int modifyUser(User user) {
        return userDAO.modifyUser(user);
    }

    /** 로그인 시 사용자 확인 */
    @Override
    public User checkUserLogin(User user) {
        return userDAO.checkUserLogin(user);
    }

    /** 이메일 중복 확인 */
    @Override
    public boolean isEmailCheck(String email) {
        return userDAO.isEmailCheck(email);
    }

    /** 검색 조건에 따른 사용자 리스트 조회 */
    @Override
    public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition) {
        return userDAO.findUserListBySearchCondition(userSearchCondition);
    }

    /** 사용자 인증 확인 (비밀번호 찾기용) */
    @Override
    public User checkUserAuth(User user) {
        return userDAO.checkUserAuth(user);
    }

    /** 비밀번호 변경 */
    @Override
    public int changeUserPassword(User user) {
        return userDAO.changeUserPassword(user);
    }

    /** 인증 코드 이메일 전송 */
    @Override
    public void sendAuthCodeToEmail(String email) {
        // TODO: 이메일 인증 코드 발송 로직 구현
    }

    /** 인증 코드 검증 */
    @Override
    public boolean verifyAuthCode(String email, int authCode) {
        // TODO: 이메일 인증 코드 검증 로직 구현
        return false;
    }

    /** 비밀번호 재설정 */
    @Override
    public int resetPassword(String email, String newPassword) {
        // TODO: 비밀번호 재설정 로직 구현
        return 0;
    }
}
