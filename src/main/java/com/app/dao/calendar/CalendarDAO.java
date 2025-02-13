package com.app.dao.calendar;

import com.app.dto.calendar.CalendarDTO;
import java.util.List;

public interface CalendarDAO {
    // 📌 로그인한 사용자의 일정 조회
    List<CalendarDTO> getUserEvents(String userEmail);

    // 📌 특정 일정 조회
    CalendarDTO getEventById(int id);

    // 📌 일정 추가
    void addEvent(CalendarDTO event);

    // 📌 로그인한 사용자의 일정만 삭제 가능
    void deleteEvent(int id, String userEmail);

    // 📌 일정 수정 (로그인한 사용자 본인의 일정만 수정 가능)
    void updateEvent(CalendarDTO event);

    // 📌 모든 일정 조회 (관리자 기능 등을 위해 남겨둠)
    List<CalendarDTO> getAllEvents();
}
