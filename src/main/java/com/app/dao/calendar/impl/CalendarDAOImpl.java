package com.app.dao.calendar.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.dao.calendar.CalendarDAO;
import com.app.dto.calendar.CalendarDTO;

@Repository
public class CalendarDAOImpl implements CalendarDAO {

    private static final String NAMESPACE = "com.app.dao.calendar.CalendarDAO.";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<CalendarDTO> getAllEvents() {
        return sqlSession.selectList(NAMESPACE + "getAllEvents");
    }

    @Override
    public void addEvent(CalendarDTO event) {
        sqlSession.insert(NAMESPACE + "addEvent", event);
    }

    // 📌 추가된 메서드 (일정 삭제)
    @Override
    public void deleteEvent(int id) {
        sqlSession.delete(NAMESPACE + "deleteEvent", id);
    }

    // 📌 추가된 메서드 (일정 수정)
    @Override
    public void updateEvent(CalendarDTO event) {
        sqlSession.update(NAMESPACE + "updateEvent", event);
    }
}
