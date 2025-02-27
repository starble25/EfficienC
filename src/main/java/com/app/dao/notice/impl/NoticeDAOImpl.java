package com.app.dao.notice.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.notice.NoticeDAO;
import com.app.dto.notice.Notice;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Notice> findNoticeList() {
		List<Notice> noticeList = sqlSessionTemplate.selectList("notice_mapper.findNoticeList");
		return noticeList;
	}

	@Override
	public int removeNotice(int id) {
		int result = sqlSessionTemplate.delete("notice_mapper.removeNotice", id);
		return result;
	}

	@Override
	public String getUserNameById(int id) {
		String name = sqlSessionTemplate.selectOne("notice_mapper.getUserNameById", id);
		return name;
	}

	@Override
	public int saveNotice(Notice notice) {
		int result = sqlSessionTemplate.insert("notice_mapper.saveNotice", notice);
		return result;
	}
	
}
