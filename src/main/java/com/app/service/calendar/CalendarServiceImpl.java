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
    public CalendarDTO getEventById(int id) {
        return calendarDAO.getEventById(id);
    }

    @Override
    public void addEvent(CalendarDTO event) {
        calendarDAO.addEvent(event);
    }

    @Override
    public void deleteEvent(int id) {
        calendarDAO.deleteEvent(id);
    }

    @Override
    public void updateEvent(CalendarDTO event) {
        calendarDAO.updateEvent(event);
    }
}
