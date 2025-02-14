package com.app.dto.user;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;


@Data
public class User {
	
	public User(String email2, String name2, String jumin2) {
		// TODO Auto-generated constructor stub
	}
	int id;
	String email;
	String pw;
	String name;
	String tel;
	String jumin;
	String positionCode;
	String deptCode;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	int cmpId;
	
}