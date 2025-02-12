package com.app.dao.calendar;

import java.util.List;
import com.app.dto.calendar.CalendarDTO;

public interface CalendarDAO {
    List<CalendarDTO> getAllEvents();
    void addEvent(CalendarDTO event);
	void deleteEvent(int id);
	void updateEvent(CalendarDTO event);
}
