package com.app.dao.user;

import java.util.List;
import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;

public interface UserDAO {
    // 📌 사용자 저장 (회원가입)
    int saveUser(User user);

    // 📌 전체 사용자 조회
    List<User> findUserList();

    // 📌 ID 기반 사용자 조회
    User findUserById(String id);

    // 📌 이메일 기반 사용자 조회 (로그인 시 사용)
    User findByEmail(String email);

    // 📌 사용자 정보 수정
    int modifyUser(User user);

    // 📌 로그인 시 사용자 확인
    User checkUserLogin(User user);

    // 📌 이메일 중복 확인
    boolean isEmailCheck(String email);

    // 📌 검색 조건에 따른 사용자 리스트 조회
    List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition);
}
