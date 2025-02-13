package com.app.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.dto.calendar.CalendarDTO;
import com.app.service.calendar.CalendarService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    // 📌 캘린더 페이지 렌더링 (JSP로 이동)
    @GetMapping
    public String showCalendar(HttpSession session, Model model) {
        String userEmail = (String) session.getAttribute("userEmail"); // 세션에서 로그인한 사용자 이메일 가져오기
        if (userEmail == null) {
            return "redirect:/login"; // 로그인 안 되어 있으면 로그인 페이지로 리디렉션
        }
        
        List<CalendarDTO> events = calendarService.getUserEvents(userEmail); // 사용자별 일정 가져오기
        model.addAttribute("events", events);
        return "calendar/calendar";
    }

    // 📌 일정 등록 폼 (새 창에서 열림)
    @GetMapping("/event-form")
    public String showEventForm() {
        return "calendar/event-form";
    }

    // 📌 로그인한 사용자의 일정 조회 (JSON 응답 - 달력에 표시됨)
    @GetMapping("/events")
    @ResponseBody
    public List<CalendarDTO> getUserEvents(HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return null;
        }
        return calendarService.getUserEvents(userEmail);
    }

    // 📌 특정 일정 조회 (ID 기반)
    @GetMapping("/event/{id}")
    @ResponseBody
    public CalendarDTO getEventById(@PathVariable("id") int id) {
        return calendarService.getEventById(id);
    }

    // 📌 일정 추가 (로그인한 사용자의 이메일 포함)
    @PostMapping("/add")
    @ResponseBody
    public String addEvent(HttpSession session,
                           @RequestParam String title, 
                           @RequestParam String startDate, 
                           @RequestParam String endDate,
                           @RequestParam(required = false, defaultValue = "기본") String category) {

        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return "error: not logged in";
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            Timestamp startDateTime = Timestamp.valueOf(LocalDateTime.parse(startDate, formatter));
            Timestamp endDateTime = Timestamp.valueOf(LocalDateTime.parse(endDate, formatter));

            CalendarDTO event = new CalendarDTO(0, title, startDateTime, endDateTime, category, userEmail);
            calendarService.addEvent(event);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // 📌 일정 삭제 기능 (로그인한 사용자의 일정만 삭제 가능)
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteEvent(HttpSession session, @PathVariable("id") int id) {
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return "error: not logged in";
        }

        try {
            CalendarDTO event = calendarService.getEventById(id);
            if (!event.getUserEmail().equals(userEmail)) {
                return "error: unauthorized"; // 다른 사람 일정 삭제 불가
            }
            calendarService.deleteEvent(id, userEmail);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // 📌 일정 수정 기능 (로그인한 사용자의 일정만 수정 가능)
    @PostMapping("/update")
    @ResponseBody
    public String updateEvent(HttpSession session,
                              @RequestParam int id,
                              @RequestParam String title,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam String category) {

        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return "error: not logged in";
        }

        try {
            CalendarDTO event = calendarService.getEventById(id);
            if (!event.getUserEmail().equals(userEmail)) {
                return "error: unauthorized"; // 다른 사람 일정 수정 불가
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            Timestamp startDateTime = Timestamp.valueOf(LocalDateTime.parse(startDate, formatter));
            Timestamp endDateTime = Timestamp.valueOf(LocalDateTime.parse(endDate, formatter));

            CalendarDTO updatedEvent = new CalendarDTO(id, title, startDateTime, endDateTime, category, userEmail);
            calendarService.updateEvent(updatedEvent);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
