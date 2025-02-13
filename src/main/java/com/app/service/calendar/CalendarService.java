package com.app.service.calendar;

import com.app.dto.calendar.CalendarDTO;
import java.util.List;

public interface CalendarService {
    List<CalendarDTO> getAllEvents();
    CalendarDTO getEventById(int id); // 📌 특정 일정 조회 추가
    void addEvent(CalendarDTO event);
    void deleteEvent(int id);
    void updateEvent(CalendarDTO event);
}
