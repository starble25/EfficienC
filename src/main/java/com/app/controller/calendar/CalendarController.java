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
@RequestMapping("/calendar")  // 📌 기본 URL = "/calendar"
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    // 📌 기본 캘린더 페이지 이동 (JSP로 렌더링)
    @GetMapping  // 📌 "/" 제거
    public String showCalendar(Model model) {
        List<CalendarDTO> events = calendarService.getAllEvents();
        model.addAttribute("events", events);
        return "calendar/calendar"; // 📌 JSP 파일 위치 확인
    }

    // 📌 일정 추가 (폼에서 데이터 받아서 처리)
    @PostMapping("/add")
    public String addEvent(@RequestParam String title, 
                           @RequestParam(required = false) String startDate, 
                           @RequestParam(required = false) String endDate) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            // 기본값 설정 (현재 시간)
            LocalDateTime now = LocalDateTime.now();

            Timestamp startDateTime = (startDate != null && !startDate.isEmpty()) 
                ? Timestamp.valueOf(LocalDateTime.parse(startDate, formatter)) 
                : Timestamp.valueOf(now); // 기본값 현재 시간

            Timestamp endDateTime = (endDate != null && !endDate.isEmpty()) 
                ? Timestamp.valueOf(LocalDateTime.parse(endDate, formatter)) 
                : Timestamp.valueOf(now.plusHours(1)); // 기본값 현재 시간 + 1시간

            // 📌 DTO 객체 생성
            CalendarDTO event = new CalendarDTO(0, title, startDateTime, endDateTime, "기본");
            calendarService.addEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/calendar"; // 📌 캘린더 페이지로 리디렉트
    }
}
