package com.app.controller.login;

import java.security.PublicKey;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	

	@PostMapping("/login")
	public String loginAction(User user, HttpSession session) {

		User loginUser = userService.checkUserLogin(user);

		if (loginUser == null) { // 아이디X? 아이디O&비번X null
			System.out.println("로그인 실패");
			return "login/login";
		} else {

			LoginManager.setSessionLogin(session, loginUser.getEmail());
			session.setAttribute("loginUserEmail", loginUser.getEmail());
			session.setAttribute("loginUserId", loginUser.getId());
		
			return "redirect:/main";
		}
	}

	@GetMapping("/logout")
	public String logoutAction(HttpSession session) {
		System.out.println("사용자 로그아웃함");
		LoginManager.logout(session);
		//session.invalidate();
		
		return "redirect:/login";
	}
}
