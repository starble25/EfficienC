package com.app.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.service.user.UserService;

@Controller
public class FindController {

	@Autowired
	UserService userService;

	@GetMapping("/findId")
	public String findId() {
		return "login/findId";
	}

	@GetMapping("/findPassword")
	public String findPw() {
		return "login/findPassword";
	}
}
