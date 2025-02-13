package com.app.dao.address.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.address.AddressDAO;
import com.app.dto.user.User;

@Repository
public class AddressDAOImpl implements AddressDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<User> findAddressUserList() {
		List<User> addressUserList = sqlSessionTemplate.selectList("address_mapper.findAddressUserList");
		return addressUserList;
	}

	
}
