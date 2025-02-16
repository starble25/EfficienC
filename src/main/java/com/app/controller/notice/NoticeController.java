package com.app.controller.notice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dto.notice.Notice;
import com.app.service.notice.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	@GetMapping
	public String notice(HttpSession session, Model model) {
//		if( session.getAttribute("loginUserId") == null ) {
//			return "redirect:/login";
//		}
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter);
		System.out.println("현재 날짜: " + formattedDate);
		
		List<Notice> noticeList = noticeService.findNoticeList();
		model.addAttribute("noticeList", noticeList);
		System.out.println(noticeList.toString());
		
		return "/notice/notice";
	}
	
	@PostMapping
	public String noticeAction(HttpSession session) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter);
		System.out.println("현재 날짜: " + formattedDate);
		
		return null;
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
