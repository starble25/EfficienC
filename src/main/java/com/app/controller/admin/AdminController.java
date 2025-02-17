package com.app.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.common.CommonCode;
import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;
import com.app.service.user.UserService;

@Controller
public class AdminController {

	@Autowired
	UserService userService;

	// 사용자 관리/등록

	@GetMapping("/admin/users/add")
	public String addUser() {

		return "admin/addUser";
	}
	
	// 사용자 추가
	@PostMapping("/admin/users/add")
	public String addUserAction(User user) {

		int result = userService.saveUser(user);
		System.out.println("사용자 추가 처리 결과 : " + result);

		if (result > 0) {
			return "redirect:/admin/users";
		} else {
			return "admin/addUser";
		}
	}

	// 사용자 목록
	@GetMapping("/admin/usersList")
	public String users(Model model) {

		// List<User> userList = userService.findUserList();
		List<User> userList = userService.findUserList();

		model.addAttribute("userList", userList);
		

		return "admin/usersList";

	}

	// 사용자 상세페이지
	@GetMapping("/admin/user/{email}")
	public String user(@PathVariable String email, Model model) {

		User user = userService.findUserByEmail(email);
		model.addAttribute("user", user);

		return "admin/user";
	}

	// 사용자정보 수정 페이지
	@GetMapping("/admin/modifyUser/{email}")
	public String modifyUser(@PathVariable String email, Model model) {

		User user = userService.findUserByEmail(email);
		model.addAttribute("user", user);

		return "admin/modifyUser";
	}

	@PostMapping("/admin/modifyUser")
	public String modifyUserAction(User user) {

		int result = userService.modifyUser(user);
		
		System.out.println(user.getEmail());
		if (result > 0) {
			return "redirect:/admin/user/" + user.getEmail();
		} else {
			return "redirect:/admin/modifyUser/" + user.getEmail();
		}

	}

}
