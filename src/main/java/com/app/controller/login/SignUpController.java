package com.app.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class SignUpController {
	@Autowired
	UserService userService;

	@GetMapping("/signup")
	public String signupUser() {
		return "login/signup";
	}

	@PostMapping("/signup")
	public String signupUserAction(User user) {
		// 값 넘어온거 확인
		System.out.println(user.toString());
		// 등록
		int result = userService.saveUser(user);
		System.out.println(result);

		if (result > 0) {
			return "redirect:/login";
		} else {
			return "login/signup";
		}
	}
}
