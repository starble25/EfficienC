package com.app.dao.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class userDAOImpl {
	@Autowired			
	SqlSessionTemplate sqlSessionTemplate;		
}
