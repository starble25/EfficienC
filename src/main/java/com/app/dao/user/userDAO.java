package com.app.dao.user;

import org.springframework.stereotype.Repository;

import com.app.dto.user.User;

@Repository
public interface userDAO {
	int saveUser(User user);
}
