package com.mobileapp.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileapp.ui.model.request.UserDetailsReqModel;
import com.mobileapp.ui.model.response.UserRest;
import com.mobileapp.userservice.UserService;
import com.mobileapp.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;

	Utils util;

	public UserServiceImpl() {
	}

	@Autowired
	public UserServiceImpl(Utils util) { // constructor based dependency injection
		this.util = util;
	}

	@Override
	public UserRest createUsers(UserDetailsReqModel userReq) {
		UserRest user = new UserRest();
		user.setEmail(userReq.getEmail());
		user.setFirstName(userReq.getFirstName());
		user.setLastName(userReq.getLastName());

		String userId = util.generateUserId();
		user.setUserId(userId);
		if (users == null)
			users = new HashMap<String, UserRest>();
		users.put(userId, user);
		return user;
	}

}
