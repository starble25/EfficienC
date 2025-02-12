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

    // 📌 캘린더 페이지 이동 (JSP 렌더링)
    @GetMapping
    public String showCalendar(Model model) {
        List<CalendarDTO> events = calendarService.getAllEvents();
        model.addAttribute("events", events);
        return "calendar/calendar"; // 📌 JSP 파일 위치
    }

    // 📌 모든 일정 조회 (JSON 응답)
    @GetMapping("/events")
    @ResponseBody
    public List<CalendarDTO> getAllEvents() {
        return calendarService.getAllEvents();
    }

    // 📌 일정 추가 (폼에서 데이터 받아서 처리)
    @PostMapping("/add")
    @ResponseBody
    public String addEvent(@RequestParam String title, 
                           @RequestParam(required = false) String startDate, 
                           @RequestParam(required = false) String endDate,
                           @RequestParam String category) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            LocalDateTime now = LocalDateTime.now();

            Timestamp startDateTime = (startDate != null && !startDate.isEmpty()) 
                ? Timestamp.valueOf(LocalDateTime.parse(startDate, formatter)) 
                : Timestamp.valueOf(now);

            Timestamp endDateTime = (endDate != null && !endDate.isEmpty()) 
                ? Timestamp.valueOf(LocalDateTime.parse(endDate, formatter)) 
                : Timestamp.valueOf(now.plusHours(1));

            // 📌 DTO 객체 생성 후 DB에 추가
            CalendarDTO event = new CalendarDTO(0, title, startDateTime, endDateTime, category);
            calendarService.addEvent(event);

            return "success"; // 성공 응답
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 실패 응답
        }
    }

    // 📌 일정 삭제 기능
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteEvent(@PathVariable("id") int id) {
        try {
            calendarService.deleteEvent(id);
            return "success"; // 삭제 성공
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 삭제 실패
        }
    }

    // 📌 일정 수정 기능
    @PostMapping("/update")
    @ResponseBody
    public String updateEvent(@RequestParam int id,
                              @RequestParam String title,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam String category) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            Timestamp startDateTime = Timestamp.valueOf(LocalDateTime.parse(startDate, formatter));
            Timestamp endDateTime = Timestamp.valueOf(LocalDateTime.parse(endDate, formatter));

            CalendarDTO event = new CalendarDTO(id, title, startDateTime, endDateTime, category);
            calendarService.updateEvent(event);

            return "success"; // 수정 성공
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 수정 실패
        }
    }
}
