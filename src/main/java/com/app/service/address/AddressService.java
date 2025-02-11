package com.app.service.address;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.user.User;

@Service
public interface AddressService {

	// Read
	List<User> findAddressUserList();
	
}
