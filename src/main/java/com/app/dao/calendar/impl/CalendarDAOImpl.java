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

    // 📌 모든 일정 조회 (관리자 기능)
    @Override
    public List<CalendarDTO> getAllEvents() {
        return sqlSession.selectList(NAMESPACE + "getAllEvents");
    }

    // 📌 특정 일정 조회
    @Override
    public CalendarDTO getEventById(int id) {
        return sqlSession.selectOne(NAMESPACE + "getEventById", id);
    }

    // 📌 일정 추가
    @Override
    public void addEvent(CalendarDTO event) {
        sqlSession.insert(NAMESPACE + "addEvent", event);
    }

    // 📌 로그인한 사용자의 일정만 조회
    @Override
    public List<CalendarDTO> getUserEvents(String userEmail) {
        return sqlSession.selectList(NAMESPACE + "getUserEvents", userEmail);
    }

    // 📌 로그인한 사용자만 자신의 일정 삭제 가능
    @Override
    public void deleteEvent(int id, String userEmail) {
        CalendarDTO event = new CalendarDTO();
        event.setId(id);
        event.setUserEmail(userEmail);
        sqlSession.delete(NAMESPACE + "deleteEvent", event);
    }

    // 📌 일정 수정 (로그인한 사용자 본인의 일정만 수정 가능)
    @Override
    public void updateEvent(CalendarDTO event) {
        sqlSession.update(NAMESPACE + "updateEvent", event);
    }
}
