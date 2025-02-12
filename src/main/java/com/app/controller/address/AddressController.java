package com.app.controller.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.dto.user.User;
import com.app.service.address.AddressService;
import com.app.service.user.UserService;

@Controller
public class AddressController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;

	@GetMapping("/address")
	public String addressMain(Model model) {
		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);
		
		System.out.println(userList.toString());
		
		List<User> addressUser = addressService.findAddressUserList();
		System.out.println("address User List");
		for(User user : addressUser) {
			System.out.println(user.toString());
		}
		
		return "address/addressMain";
	}
}
