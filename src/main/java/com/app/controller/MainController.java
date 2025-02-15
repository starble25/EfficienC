package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/main")
	public String main() {
		return "pages/main";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:pages/main";
	}
	
}
