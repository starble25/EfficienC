package com.app.dao.address;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.dto.address.Address;
import com.app.dto.user.User;

@Repository
public interface AddressDAO {

	public List<User> findAddressUserList(int loginUserId);
	List<User> findUserListBySearch(String searchKeyword);
	
	int removeUser(int userId);
	
	int saveUser(Address address);
	
}
