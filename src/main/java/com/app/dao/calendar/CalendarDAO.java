package com.app.dao.calendar;

import com.app.dto.calendar.CalendarDTO;
import java.util.List;

public interface CalendarDAO {
    List<CalendarDTO> getUserEvents(String userEmail); // 📌 로그인한 사용자의 일정만 조회
    CalendarDTO getEventById(int id);
    void addEvent(CalendarDTO event);
    void deleteEvent(int id, String userEmail); // 📌 로그인한 사용자만 삭제 가능하도록 변경
    void updateEvent(CalendarDTO event);
	List<CalendarDTO> getAllEvents();
	void deleteEvent(int id);
}
