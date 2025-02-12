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
	
	@GetMapping("/admin")
	public String admin() {
		return "admin/admin";
	}

	
	//사용자 관리/등록
	
		@GetMapping("/admin/users/add")
		public String addUser() {
			
			return "admin/addUser";
		}
		
		@PostMapping("/admin/users/add")
		public String addUserAction(User user) {
			//사용자 추가 (관리자X)
			
//			//유효성 검증
//			if(user.getId().length() < 2) {
//				return "admin/addUser";
//			}
//			
//			if(user.getName().length() > 10) {
//				return "admin/addUser";
//			}
//			
//			user.setUserType(CommonCode.USER_USERTYPE_CUSTOMER);
//			int result = userService.saveUser(user);
			int result = userService.saveUser(user);
			System.out.println("사용자 추가 처리 결과 : " + result);
			
			if(result > 0) {
				return "redirect:/admin/users";
			} else {
				return "admin/addUser";			
			}
		}
		
		//사용자 목록
		@GetMapping("/admin/users")
		public String users(Model model, UserSearchCondition userSearchCondition) {
			
			System.out.println(userSearchCondition);
			
			//List<User> userList = userService.findUserList();
			List<User> userList = userService.findUserListBySearchCondition(userSearchCondition);
			
			
			model.addAttribute("userList", userList);
			model.addAttribute("userSearchCondition", userSearchCondition);
			
			return "admin/users";
			
		}
		
//		//사용자 상세페이지
//		@GetMapping("/admin/user/{id}")
//		public String user(@PathVariable String id, Model model) {
//			
//			User user = userService.findUserById(id);
//			model.addAttribute("user", user);
//			
//			return "admin/user";
//		}
//		
//		//사용자정보 수정 페이지
//		@GetMapping("/admin/modifyUser/{id}")
//		public String modifyUser(@PathVariable String id, Model model) {
//			
//			User user = userService.findUserById(id);
//			model.addAttribute("user", user);
//			
//			return "admin/modifyUser";
//		}
		
		@PostMapping("/admin/modifyUser")
		public String modifyUserAction(User user) {
			
			System.out.println(user);
			
			int result = userService.modifyUser(user);
			
			if(result > 0 ) {
				return "redirect:/admin/user/" + user.getId();
			} else {
				return "redirect:/admin/modifyUser/" + user.getId();
			}
			
		}
		
		
	/*
	 * 관리자 : 1. id 와 pw 가 동시에 일치하는 사용자 users Table find 2. users Table -> cmp_id 체크,
	 * admin 권한 체크 후 사이트 이동 //권한 있을 시 navbar에 관리자<->사용자 사이트 이동 자유롭게
	 * 
	 * 1. id 검색 -> 사용자 users Table find 2. 회사코드 부여된 사용자 List -> users Table find (
	 * 회사 코드) 3. 사용자 삭제 및 직급, 부서 수정
	 * 
	 */
}
