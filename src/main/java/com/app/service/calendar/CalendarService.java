package com.app.service.calendar;

import com.app.dto.calendar.CalendarDTO;
import java.util.List;

public interface CalendarService {
    // 📌 로그인한 사용자의 일정 조회
    List<CalendarDTO> getUserEvents(String userEmail);

    // 📌 특정 일정 조회
    CalendarDTO getEventById(int id);

    // 📌 일정 추가
    void addEvent(CalendarDTO event);

    // 📌 로그인한 사용자만 일정 삭제 가능하도록 변경
    void deleteEvent(int id);

    // 📌 일정 수정 (로그인한 사용자만 수정 가능)
    void updateEvent(CalendarDTO event);

	void deleteEvent(int id, String userEmail);
}
