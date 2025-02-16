package com.app.service.notice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.notice.NoticeDAO;
import com.app.dto.notice.Notice;
import com.app.service.notice.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDAO noticeDAO;
	
	@Override
	public List<Notice> findNoticeList() {
		List<Notice> noticeList = noticeDAO.findNoticeList();
		return noticeList;
	}

	@Override
	public int removeNotice(int id) {
		int result = noticeDAO.removeNotice(id);
		return result;
	}

}
