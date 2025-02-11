package com.app.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.dto.calendar.CalendarDTO;
import com.app.service.calendar.CalendarService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    // 일정 목록 가져오기 (JSP 페이지 이동)
    @GetMapping
    public String showCalendar(Model model) {
        List<CalendarDTO> events = calendarService.getAllEvents();
        model.addAttribute("events", events);
        return "calendar/calendar"; // calendar.jsp로 이동
    }

    // 일정 추가 (폼에서 데이터 받아서 처리)
    @PostMapping("/add")
    public String addEvent(@RequestParam String title, 
                           @RequestParam(required = false) String startDate, 
                           @RequestParam(required = false) String endDate) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            
            Timestamp startDateTime = (startDate != null && !startDate.isEmpty()) 
                ? Timestamp.valueOf(LocalDateTime.parse(startDate, formatter)) 
                : null;

            Timestamp endDateTime = (endDate != null && !endDate.isEmpty()) 
                ? Timestamp.valueOf(LocalDateTime.parse(endDate, formatter)) 
                : null;

            CalendarDTO event = new CalendarDTO(0, title, startDateTime, endDateTime, "기본");
            calendarService.addEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/calendar"; // 일정 추가 후 다시 달력 페이지로 이동
    }
}
