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

    // 📌 로그인한 사용자의 일정 조회
    @Override
    public List<CalendarDTO> getUserEvents(String userEmail) {
        return calendarDAO.getUserEvents(userEmail);
    }

    // 📌 특정 일정 조회
    @Override
    public CalendarDTO getEventById(int id) {
        return calendarDAO.getEventById(id);
    }

    // 📌 일정 추가
    @Override
    public void addEvent(CalendarDTO event) {
        calendarDAO.addEvent(event);
    }

    // 📌 로그인한 사용자의 일정만 삭제 가능
    @Override
    public void deleteEvent(int id, String userEmail) {
        CalendarDTO event = calendarDAO.getEventById(id);
        
        // 🔹 일정이 존재하고, 해당 일정의 userEmail과 로그인한 userEmail이 같을 경우만 삭제
        if (event != null && event.getUserEmail().equals(userEmail)) {
            calendarDAO.deleteEvent(id, userEmail);
        }
    }

    // 📌 일정 수정 (로그인한 사용자만 가능)
    @Override
    public void updateEvent(CalendarDTO event) {
        CalendarDTO existingEvent = calendarDAO.getEventById(event.getId());
        
        // 🔹 기존 일정이 존재하고, 로그인한 사용자 본인의 일정만 수정 가능
        if (existingEvent != null && existingEvent.getUserEmail().equals(event.getUserEmail())) {
            calendarDAO.updateEvent(event);
        }
    }

	@Override
	public void deleteEvent(int id) {
		// TODO Auto-generated method stub
		
	}
}
