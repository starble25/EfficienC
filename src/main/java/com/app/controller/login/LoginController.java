package com.app.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class LoginController {

	// id 와 pw 가 동시에 일치하는 사용자 users Table find
	// users Table -> cmp_id 체크 후 사이트 이동
	@Autowired
	UserService userService;

	@GetMapping("/register")
	public String registerUser() {
		return "login/register";
	}

	@PostMapping("/register")
	public String registerUserAction(User user) {
		// 값 넘어온거 확인
		System.out.println(user.toString());
		// 등록
		int result = userService.saveUser(user);
		System.out.println(result);

		if (result > 0) {
			return "redirect:/login";
		} else {
			return "/register";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "login/login";
	}

	@GetMapping("/findPassword")
	public String findPw() {
		return "login/findPassword";
	}

	@PostMapping("/findPassword")
	public String findPw2() {
		return "login/findPassword2";
	}
	/*
	 * 비밀번호 찾기 : 1. random()으로 임시비밀번호 발급 2. 해당 id 와 임시비밀번호 users Table 저장 3. 로그인 후
	 * 비밀번호 재설정 -> users Table 저장 4. 로그인 화면 이동
	 */

}
