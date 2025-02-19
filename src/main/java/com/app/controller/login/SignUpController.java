package com.app.controller.login;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String signupUserAction(@Valid @ModelAttribute User user, BindingResult br) {
		System.out.println(user.getJumin());
		System.out.println(user.getTel());
		
		if(br.hasErrors()) {
			List<ObjectError> errorList =  br.getAllErrors();
			for(ObjectError er : errorList) {
				System.out.println(er.getObjectName());
				System.out.println(er.getDefaultMessage());
				System.out.println(er.getCode());
				System.out.println(er.getCodes()[0]);
			}
				return "login/signup";
		} else {		
			// 등록
			int result = userService.saveUser(user);
				return "redirect:/login";
	
		}
	}
}
	
