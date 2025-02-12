package com.app.service.calendar;

import com.app.dto.calendar.CalendarDTO;
import java.util.List;

public interface CalendarService {
    List<CalendarDTO> getAllEvents();
    void addEvent(CalendarDTO event);
    void deleteEvent(int id);  // ✅ 추가
    void updateEvent(CalendarDTO event);  // ✅ 추가
}
