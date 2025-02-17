package com.app.dto.board;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class Board {
	  private int id;
	    private String title;
	    private String content;
	    private String fileName;
	    private Date createdAt;
	    
	    private MultipartFile uploadFile; // 파일 업로드 객체

	    //setCreatedAt 2줄 추가
		public void setCreatedAt(Timestamp timestamp) {
		}
		public void setCreatedAt(Date date) {
		}

	    // Getter & Setter
//	    public int getId() { return id; }
//	    public void setId(int id) { this.id = id; }
//	    
//	    public String getTitle() { return title; }
//	    public void setTitle(String title) { this.title = title; }
//	    
//	    public String getContent() { return content; }
//	    public void setContent(String content) { this.content = content; }
//	    
//	    public String getFileName() { return fileName; }
//	    public void setFileName(String fileName) { this.fileName = fileName; }
//
//	    public Date getCreatedAt() { return createdAt; }
//	    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
//
//	    public MultipartFile getUploadFile() { return uploadFile; }
//	    public void setUploadFile(MultipartFile uploadFile) { this.uploadFile = uploadFile; }
	}
