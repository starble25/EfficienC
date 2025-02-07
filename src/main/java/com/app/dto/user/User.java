package com.app.dto.user;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
	
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
	int cmp_id;
	
}
