package com.app.service.address;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.address.Address;
import com.app.dto.user.User;

@Service
public interface AddressService {
	// Create
	int saveAddress(Address address);

	// Read
	List<User> findAddressUserList(int loginUserId);
	List<User> findUserListBySearch(String searchKeyword); // 이름 or 이메일로 users 테이블 전체조회
	
	// DELETE
	// userId = Address DB TABLE : addId
	int removeUser(int userId);
	
}
