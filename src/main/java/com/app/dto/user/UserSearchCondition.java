package com.app.dto.user;

import lombok.Data;

@Data
public class UserSearchCondition {
	
	String searchKeyword;
	String email;
	String name;
	String positionCode;
	String keyword;
}