package com.app.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.dto.calendar.CalendarDTO;
import com.app.service.calendar.CalendarService;
import com.app.service.user.UserService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    UserService userService;

    @Autowired
    CalendarService calendarService;

    // 캘린더 페이지 렌더링 (JSP로 이동)
    @GetMapping("/calendar")
    public String showCalendar(Model model, HttpSession session) {
        System.out.println("sesson.loginUserId : " + session.getAttribute("loginUserId"));
        if (session.getAttribute("loginUserId") == null) {
            return "redirect:/login";
        }
        List<CalendarDTO> events = calendarService.getAllEvents();
        model.addAttribute("events", events);
        return "calendar/calendar";
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
