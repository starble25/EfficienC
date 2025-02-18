package com.app.controller.calendar;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.MenuItem;
import com.app.dto.calendar.CalendarDTO;
import com.app.service.calendar.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    
    @Autowired
    CalendarService calendarService;

    // 📌 캘린더 페이지 렌더링 (JSP로 이동)
    @GetMapping
    public String showCalendar(Model model,  HttpSession session) {
    	//loginUserId값 가져오기
        if (session.getAttribute("loginUserId") == null) {
            return "redirect:/login";
            
        } else {
        	
	    	List<MenuItem> menuList = new ArrayList<>();
	        menuList.add(new MenuItem("홈", "/main", false));
	        menuList.add(new MenuItem("공지사항", "/notice", false));
	        menuList.add(new MenuItem("사내게시판", "/board", false));
	        menuList.add(new MenuItem("마이페이지", "/mypage", false));
	        menuList.add(new MenuItem("캘린더", "/calendar", false));
	        menuList.add(new MenuItem("ToDoList", "/task/list", false)); // true로 변경하면 현재 활성화
	        menuList.add(new MenuItem("주소록", "/address", false));
	        menuList.add(new MenuItem("전자결제", "/payment", false));
	
	        // request 스코프에 저장하여 JSP에서 사용 가능하도록 함
	        model.addAttribute("menuList", menuList);
	        
	        List<CalendarDTO> events = calendarService.getAllEvents();
	        model.addAttribute("events", events);
	        return "calendar/calendar";
        }
        
    }

    // 일정 등록 폼 (새 창에서 열림)
    @GetMapping("/event-form")
    public String showEventForm(HttpSession session) {
        System.out.println("sesson.loginUserId : " + session.getAttribute("loginUserId"));
        if (session.getAttribute("loginUserId") == null) {
            return "redirect:/login";
        }
        return "calendar/event-form";
    }

    // 모든 일정 조회 (JSON 응답 - 달력에 표시됨)
    @GetMapping("/events")
    @ResponseBody
    public Object getAllEvents(HttpSession session) {
        System.out.println("sesson.loginUserId : " + session.getAttribute("loginUserId"));
        if (session.getAttribute("loginUserId") == null) {
            return "redirect:/login";
        }
        return calendarService.getAllEvents();
    }

    // 특정 일정 조회 (ID 기반)
    @GetMapping("/event/{id}")
    @ResponseBody
    public Object getEventById(@PathVariable("id") int id, HttpSession session) {
        System.out.println("sesson.loginUserId : " + session.getAttribute("loginUserId"));
        if (session.getAttribute("loginUserId") == null) {
            return "redirect:/login";
        }
        return calendarService.getEventById(id);
    }

    // 일정 추가 (모달창에서 등록된 데이터 저장)
    @PostMapping("/add")
    @ResponseBody
    public String addEvent(@RequestParam String title, 
                           @RequestParam String startDate, 
                           @RequestParam String endDate,
                           @RequestParam(required = false, defaultValue = "기본") String category,
                           HttpSession session) {
        System.out.println("sesson.loginUserId : " + session.getAttribute("loginUserId"));
        if (session.getAttribute("loginUserId") == null) {
            return "redirect:/login";
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            Timestamp startDateTime = Timestamp.valueOf(LocalDateTime.parse(startDate, formatter));
            Timestamp endDateTime = Timestamp.valueOf(LocalDateTime.parse(endDate, formatter));

            CalendarDTO event = new CalendarDTO(0, title, startDateTime, endDateTime, category);
            calendarService.addEvent(event);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // 일정 삭제 기능
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteEvent(@PathVariable("id") int id, HttpSession session) {
        System.out.println("sesson.loginUserId : " + session.getAttribute("loginUserId"));
        if (session.getAttribute("loginUserId") == null) {
            return "redirect:/login";
        }
        try {
            calendarService.deleteEvent(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // 일정 수정 기능
    @PostMapping("/update")
    @ResponseBody
    public String updateEvent(@RequestParam int id,
                              @RequestParam String title,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam String category,
                              HttpSession session) {
        System.out.println("sesson.loginUserId : " + session.getAttribute("loginUserId"));
        if (session.getAttribute("loginUserId") == null) {
            return "redirect:/login";
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            Timestamp startDateTime = Timestamp.valueOf(LocalDateTime.parse(startDate, formatter));
            Timestamp endDateTime = Timestamp.valueOf(LocalDateTime.parse(endDate, formatter));

            CalendarDTO event = new CalendarDTO(id, title, startDateTime, endDateTime, category);
            calendarService.updateEvent(event);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
