package com.app.dto.address;

import lombok.Data;

@Data
public class Address {
	
	int id; // Address PK
	int myId; // 내 계정 Users PK id
	int addId; // 추가한 계정 Users PK id
	String favorite;
	
}
