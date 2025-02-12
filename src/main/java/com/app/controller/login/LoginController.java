package com.app.controller.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.user.User;
import com.app.service.user.UserService;
import com.app.util.LoginManager;

@Controller
public class LoginController {

	// id 와 pw 가 동시에 일치하는 사용자 users Table find
	// users Table -> cmp_id 체크 후 사이트 이동
	@Autowired
	UserService userService;


	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		LoginManager.logout(session);
		
		return "redirect:/login";
	}
	

	@PostMapping("/login")
	public String loginAction(User user, HttpSession httpSession) {

		User loginUser = userService.checkUserLogin(user);

		if (loginUser == null) { // 아이디X? 아이디O&비번X null
			System.out.println("로그인 실패");
			return "login/login";
		} else {

			LoginManager.setSessionLogin(httpSession, loginUser.getEmail());
			System.out.println(loginUser.getEmail() + "사용자 로그인함");

			return "redirect:/main";
		}
	}

}
