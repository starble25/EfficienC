package com.app.controller.address;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {

	@GetMapping("/address")
	public String addressMain() {
		
		return "address/addressMain";
	}
}
