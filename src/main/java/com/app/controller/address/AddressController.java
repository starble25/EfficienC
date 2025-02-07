package com.app.controller.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.dto.user.User;
import com.app.service.UserService;

@Controller
public class AddressController {
	
	@Autowired
	UserService userService;	

	@GetMapping("/address")
	public String addressMain(Model model) {
		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);
		
		System.out.println(userList.toString());
		
		return "address/addressMain";
	}
}
