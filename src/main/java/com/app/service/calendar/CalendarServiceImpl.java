package com.app.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.calendar.CalendarDAO;
import com.app.dto.calendar.CalendarDTO;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarDAO calendarDAO;

    @Override
    public List<CalendarDTO> getAllEvents() {
        return calendarDAO.getAllEvents();
    }

    @Override
    public void addEvent(CalendarDTO event) {
        calendarDAO.addEvent(event);
    }

    @Override
    public void deleteEvent(int id) {  // ✅ 삭제 기능 추가
        calendarDAO.deleteEvent(id);
    }

    @Override
    public void updateEvent(CalendarDTO event) {  // ✅ 일정 수정 기능 추가
        calendarDAO.updateEvent(event);
    }
}
