package com.app.dto.notice;

import lombok.Data;

@Data
public class Notice {

	int id;			// PK
	String title;	// 제목
	String content;	// 내용
	String author;	// 작성자
	String regDate;	// 작성시간 String(yyyy-MM-dd)
	
}
