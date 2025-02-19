package com.app.controller.notice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dto.MenuItem;
import com.app.dto.notice.Notice;
import com.app.service.notice.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	@GetMapping
	public String notice(HttpSession session, Model model, HttpServletRequest request) {
		if( session.getAttribute("loginUserId") == null ) {
			return "redirect:/login";
		};
		
		//sidebar
		List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("홈", "/main", false));
        menuList.add(new MenuItem("공지사항", "/notice", false));
        menuList.add(new MenuItem("사내게시판", "/board/list", false));
        menuList.add(new MenuItem("마이페이지", "/mypage", false));
        menuList.add(new MenuItem("캘린더", "/calendar", false));
        menuList.add(new MenuItem("ToDoList", "/tasks", false)); // 현재 활성화
        menuList.add(new MenuItem("주소록", "/address", false));
        menuList.add(new MenuItem("전자결제", "/payment", false));

        // request 스코프에 저장하여 JSP에서 사용 가능하도록 함
        request.setAttribute("menuList", menuList);
        //
		
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter);
		System.out.println("현재 날짜: " + formattedDate);
		
		List<Notice> noticeList = noticeService.findNoticeList();
		model.addAttribute("noticeList", noticeList);
		System.out.println(noticeList.toString());
		
		return "/notice/notice";
	}
	
	// 공지 추가
	@PostMapping
	public ResponseEntity<String> noticeAction(HttpSession session, @RequestBody Notice inputNotice) {
		int loginUserId = (int)session.getAttribute("loginUserId"); // 사용자 id
		String author = noticeService.getUserNameById(loginUserId); // 사용자 이름
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter); // 현재시간
		
		Notice notice = new Notice();
//		int id;			// PK
//		String title;	// 제목
//		String content;	// 내용
//		String author;	// 작성자
//		String regDate;	// 작성시간 String(yyyy-MM-dd)
		System.out.println("loginUserId : " + loginUserId);
		System.out.println("author : " + author);
		System.out.println("param title : " + inputNotice.getTitle());
		System.out.println("param content : " + inputNotice.getContent());
		System.out.println("formattedDate : " + formattedDate);
		System.out.println("=============");
		
		notice.setTitle(inputNotice.getTitle());
		notice.setContent(inputNotice.getContent());
		notice.setAuthor(author);
		notice.setRegDate(formattedDate);
		
		int result = noticeService.saveNotice(notice);
		
	    if ( result > 0 ) {
	        System.out.println("saveNotice 성공");
	        return ResponseEntity.ok("공지추가 성공");
	    } else {
	        System.out.println("saveNotice 실패");
	        return ResponseEntity.status(500).body("공지추가 실패");
	    }
	}
	
	// 공지 삭제
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		int result = noticeService.removeNotice(id);
	    if ( result > 0 ) {
	        System.out.println("removeNotice 성공");
	        return ResponseEntity.ok("삭제 성공");
	    } else {
	        System.out.println("removeNotice 실패");
	        return ResponseEntity.status(500).body("삭제 실패");
	    }
	}
	
}
