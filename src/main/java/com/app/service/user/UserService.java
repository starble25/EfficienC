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
    User findUserByEmail(String email); // 이메일 기반 사용자 조회 (로그인 시 사용)

    // 📌 Update (회원 정보 수정)
    int modifyUser(User user);
    int changeUserPassword(User user); // 비밀번호 변경

    // 📌 로그인 관련 기능
    boolean isEmailCheck(String getEmail); // 이메일 중복 확인
    User checkUserLogin(User user); // 사용자 로그인 확인
    User checkUserAuth(User user); // 사용자 인증 확인 (비밀번호 찾기용)

    // 📌 조건 검색 (검색 조건 기반 사용자 리스트 조회)
    List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition);

    // 📌 추가된 비밀번호 찾기 기능
    void sendAuthCodeToEmail(String email); // 인증 코드 전송
    boolean verifyAuthCode(String email, int authCode); // 인증 코드 검증
    int resetPassword(String email, String newPassword); // 비밀번호 초기화
}
