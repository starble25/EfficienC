package com.app.controller.address;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.user.User;
import com.app.service.address.AddressService;
import com.app.service.user.UserService;

@Controller
public class AddressController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	// address 메인 페이지 연결
	@GetMapping("/address")
	public String addressMain(Model model) {
		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);
		
		// 주소록에 있는 userList 조회
		List<User> addressUserList = addressService.findAddressUserList();
		model.addAttribute("addressUserList", addressUserList);
		
		System.out.println("address User List");
		for(User user : addressUserList) {
			System.out.println(user.toString());
		}
		
//		showAddress : 1 = 내 주소록, 2 = 검색결과 주소록
		int showAddress = 1;
		model.addAttribute("showAddress", showAddress);
		
		return "address/addressMain";
	}
	
	@PostMapping("/address")
	public String addressMainAction(Model model, HttpServletRequest request) {
		
		System.out.println("/address POST 요청 들어옴");
		System.out.println("searchKeyword 값 : " + request.getParameter("searchKeyword"));
		
		int showAddress = 2;
		model.addAttribute("showAddress", showAddress);
		String searchKeyword = request.getParameter("searchKeyword").trim();
		
		if( searchKeyword == null ) {
			return "redirect:/address";
			
		} else if( searchKeyword == "" ) { // 공백이면 전체 리스트 출력
			List<User> userList = userService.findUserList();
			model.addAttribute("userList", userList);
			
			return "address/addressMain";
		} else { // 검색
			List<User> userList = addressService.findUserListBySearch(searchKeyword);
			model.addAttribute("userList", userList);
			
			return "address/addressMain";
		}
		
	}
	
	// 주소록 삭제
	@GetMapping("address/removeUser")
	public String deleteUser(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        int result = addressService.removeUser(Integer.parseInt(userId));
        
        if( result > 0 ) {
        	System.out.println("Address.addId : " + userId + ", 삭제 완료");
        } else {
        	System.out.println("삭제 실패");
        }
        
        return "redirect:/address";
	}
	
	// 주소록 추가
	@ResponseBody
	@PostMapping("address/addUser")
	public void addUser(@RequestBody String data) {
		System.out.println("address/adddUser ajax post");
		System.out.println("data : " + data);
	}
	
}
