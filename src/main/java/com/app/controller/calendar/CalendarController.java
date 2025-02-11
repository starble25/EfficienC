package com.app.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.calendar.CalendarDTO;
import com.app.service.calendar.CalendarService;

import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        List<CalendarDTO> events = calendarService.getAllEvents();
        model.addAttribute("events", events);
        return "calendar/calendar";
    }

    @PostMapping("/calendar/add")
    public String addEvent(@RequestParam String title, 
                           @RequestParam String startDate, 
                           @RequestParam String endDate) {
        CalendarDTO event = new CalendarDTO(0, title, startDate, endDate);
        calendarService.addEvent(event);
        return "redirect:/calendar";
    }
}
