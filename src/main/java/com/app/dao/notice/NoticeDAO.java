package com.app.dao.notice;

import java.util.List;

import com.app.dto.notice.Notice;

public interface NoticeDAO {
	
	List<Notice> findNoticeList();
	
	int removeNotice(int id);
	
	String getUserNameById(int id);
	
	int saveNotice(Notice notice);
}
