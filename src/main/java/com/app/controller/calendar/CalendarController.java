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

    // 📌 캘린더 페이지 렌더링 (로그인한 사용자 일정만 표시)
    @GetMapping
    public String showCalendar(HttpSession session, Model model) {
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return "redirect:/login"; // 로그인 안 되어 있으면 로그인 페이지로 이동
        }

        List<CalendarDTO> events = calendarService.getUserEvents(userEmail);
        model.addAttribute("events", events);
        return "calendar/calendar";
    }

    // 📌 로그인한 사용자의 일정 조회 (JSON 응답 - 달력에 표시)
    @GetMapping("/events")
    @ResponseBody
    public List<CalendarDTO> getUserEvents(HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return null;
        }
        return calendarService.getUserEvents(userEmail);
    }

    // 📌 일정 추가 (로그인한 사용자의 일정 저장)
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
            return "error: failed to add event";
        }
    }

    // 📌 일정 삭제 (본인의 일정만 삭제 가능)
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteEvent(HttpSession session, @PathVariable("id") int id) {
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return "error: not logged in";
        }

        try {
            CalendarDTO event = calendarService.getEventById(id);
            if (event == null) {
                return "error: event not found";
            }

            if (!event.getUserEmail().equals(userEmail)) {
                return "error: unauthorized";
            }
            
            calendarService.deleteEvent(id, userEmail);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error: failed to delete event";
        }
    }
}
