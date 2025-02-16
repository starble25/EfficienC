package com.app.service.notice;

import java.util.List;

import com.app.dto.notice.Notice;

public interface NoticeService {
	
	List<Notice> findNoticeList();
	
	int removeNotice(int id);
}
