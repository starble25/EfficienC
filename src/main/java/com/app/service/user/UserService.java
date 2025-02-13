package com.app.service.user;

import java.util.List;
import org.springframework.stereotype.Service;
import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;

@Service
public interface UserService {
    
    // 📌 Create (회원가입)
    int saveUser(User user);
    
    // 📌 Read (회원 정보 조회)
    List<User> findUserList(); // 전체 사용자 조회
    User findUserById(String id); // ID 기반 사용자 조회
    User findByEmail(String email); // 📌 이메일 기반 사용자 조회 (로그인 시 사용)
    
    // 📌 Update (회원 정보 수정)
    int modifyUser(User user);
    
    // 📌 로그인 관련 기능
    boolean isEmailCheck(String getEmail); // 이메일 중복 확인
    User checkUserLogin(User user); // 사용자 로그인 확인

    // 📌 조건 검색 (검색 조건 기반 사용자 리스트 조회)
    List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition);
}
