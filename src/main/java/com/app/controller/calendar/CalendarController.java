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

    // 📌 캘린더 페이지 렌더링
    @GetMapping
    public String showCalendar(Model model) {
        List<CalendarDTO> events = calendarService.getAllEvents();
        model.addAttribute("events", events);
        return "calendar/calendar";
    }

    // 📌 모든 일정 조회 (JSON 응답)
    @GetMapping("/events")
    @ResponseBody
    public List<CalendarDTO> getAllEvents() {
        return calendarService.getAllEvents();
    }

    // 📌 일정 추가 (모달창에서 등록된 데이터 저장)
    @PostMapping("/add")
    @ResponseBody
    public String addEvent(@RequestParam String title, 
                           @RequestParam String startDate, 
                           @RequestParam String endDate,
                           @RequestParam String category) {

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

    // 📌 일정 삭제 기능
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteEvent(@PathVariable("id") int id) {
        try {
            calendarService.deleteEvent(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
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
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
