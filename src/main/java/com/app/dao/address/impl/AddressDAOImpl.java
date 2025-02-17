package com.app.dao.address.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.address.AddressDAO;
import com.app.dto.address.Address;
import com.app.dto.user.User;

@Repository
public class AddressDAOImpl implements AddressDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<User> findAddressUserList(int loginUserId) {
		List<User> addressUserList = sqlSessionTemplate.selectList("address_mapper.findAddressUserList", loginUserId);
		return addressUserList;
	}

	@Override
	public int removeUser(int userId) {
		int result = sqlSessionTemplate.delete("address_mapper.removeUser", userId);
		return result;
	}

	@Override
	public List<User> findUserListBySearch(String searchKeyword) {
		List<User> addressUserList = sqlSessionTemplate.selectList("address_mapper.findUserListBySearch", searchKeyword);
		return addressUserList;
	}

	@Override
	public int saveUser(Address address) {
		int result = sqlSessionTemplate.insert("address_mapper.saveUser", address);
		return result;
	}

	
}
