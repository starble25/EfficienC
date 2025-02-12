package com.app.service.address.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.address.AddressDAO;
import com.app.dto.user.User;
import com.app.service.address.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressDAO addressDAO;

	@Override
	public List<User> findAddressUserList() {
		List<User> addressUserList = addressDAO.findAddressUserList();
		return addressUserList;
	}

	@Override
	public int removeUser(int userId) {
		int result = addressDAO.removeUser(userId);
		return result;
	}

	@Override
	public List<User> findUserListBySearch(String searchKeyword) {
		List<User> addressUserList = addressDAO.findUserListBySearch(searchKeyword);
		return addressUserList;
	}
	
	
}
