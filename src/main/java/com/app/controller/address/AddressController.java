package com.app.controller.address;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.MenuItem;
import com.app.dto.address.Address;
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
	public String addressMain(Model model, HttpSession session, HttpServletRequest request) {
		//sidebar
		List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("홈", "/main", false));
        menuList.add(new MenuItem("공지사항", "/notice", false));
        menuList.add(new MenuItem("사내게시판", "/board/list", false));
        menuList.add(new MenuItem("캘린더", "/calendar", false));
        menuList.add(new MenuItem("ToDoList", "/tasks", false)); // 현재 활성화
        menuList.add(new MenuItem("주소록", "/address", false));

        // request 스코프에 저장하여 JSP에서 사용 가능하도록 함
        request.setAttribute("menuList", menuList);
        //
		
		if( session.getAttribute("loginUserId") == null ) {
			return "redirect:/login";
		}
		int loginUserId = (int) session.getAttribute("loginUserId");
		
		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);
		
		// 주소록에 있는 userList 조회
		List<User> addressUserList = addressService.findAddressUserList(loginUserId);
		model.addAttribute("addressUserList", addressUserList);
		
//		showAddress : 1 = 내 주소록, 2 = 검색결과 주소록
		int showAddress = 1;
		model.addAttribute("showAddress", showAddress);
		
		return "address/addressMain";
	}
	
	@PostMapping("/address")
	public String addressMainAction(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("/address POST 요청 들어옴");
		System.out.println("searchKeyword 값 : " + request.getParameter("searchKeyword"));
		
		// 내 주소록 리스트 추가
		if( session.getAttribute("loginUserId") == null ) {
			return "redirect:/login";
		}
		int loginUserId = (int) session.getAttribute("loginUserId");
		List<User> addressUserList = addressService.findAddressUserList(loginUserId);
		model.addAttribute("addressUserList", addressUserList);
		System.out.println(addressUserList);
		
		// 검색결과 주소록
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
	public void addUser(HttpSession session, @RequestBody String data) {
		System.out.println("address/addUser ajax post");
		System.out.println("data(추가할 유저 id) : " + data);
		
		if( session.getAttribute("loginUserId") != null ) {
			
			int loginUserId = (int) session.getAttribute("loginUserId");
			
			Address address = new Address();
			address.setMyId(loginUserId);
			address.setAddId(Integer.parseInt(data));
			address.setFavorite("F");
			
			int result = addressService.saveAddress(address);
			
			if( result > 0 ) {
				System.out.println("주소록 저장 성공");
			} else {
				System.out.println("주소록 저장 실패");
			}
		}

	}
	
}
