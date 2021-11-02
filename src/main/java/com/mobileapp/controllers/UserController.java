package com.mobileapp.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mobileapp.ui.model.request.UserDetailsReqModel;
import com.mobileapp.ui.model.response.UserRest;
import com.mobileapp.userservice.UserService;
import com.mobileapp.userservice.impl.UserServiceImpl;

@RestController
@RequestMapping("users")
public class UserController {

	Map<String, UserRest> users;
	
	@Autowired
	UserService uService;

	@GetMapping
	public String getUsers() {
		return "Get called..";
	}

	@GetMapping("get/{userID}")
	public String getUser(@PathVariable String userID) {
		return "Get called with ID = " + userID;
	}

	@GetMapping(path = "param/{userID}")
	public String findUser(@RequestParam(value = "page") int page,
			@RequestParam(value = "limit", defaultValue = "1") int limit, // giving default value if not given in
																			// request
			@RequestParam(value = "sort", required = false) String sort) { // making sort optional param
		return "Get called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}

	@GetMapping(path = "getuser/{userID}")
	public UserRest findingUser(@PathVariable String userID) {

		UserRest user = new UserRest();
		user.setEmail("ask@admin.com");
		user.setFirstName("Admin");
		user.setLastName("nimbda");

		return user;
	}

	@GetMapping(path = "{userID}")
	public ResponseEntity<UserRest> findUsers(@PathVariable String userID) {

		if (users.containsKey(userID))
			return new ResponseEntity<UserRest>(users.get(userID), HttpStatus.OK);

		else
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);

	}

	@PostMapping
	public String createUser() {
		return "Post called..";
	}

	@PostMapping("create")
	public ResponseEntity<UserRest> createUsers(@Valid @RequestBody UserDetailsReqModel userReq) {

//		UserRest user = new UserRest();
//		user.setEmail(userReq.getEmail());
//		user.setFirstName(userReq.getFirstName());
//		user.setLastName(userReq.getLastName());
//
//		String userId = UUID.randomUUID().toString();
//		user.setUserId(userId);
//		if (users == null)
//			users = new HashMap<String, UserRest>();
//		users.put(userId, user);
		
		UserRest user = uService.createUsers(userReq);	
		
		return new ResponseEntity<UserRest>(user, HttpStatus.OK);
	}

	@PutMapping
	public String updateUser() {
		return "Update called..";
	}

	@PutMapping(path = "/{userId}")
	public UserRest updateUsers(@PathVariable String userId, @RequestBody UserDetailsReqModel userReq) {
		
		UserRest updateUser = users.get(userId);
		
		updateUser.setFirstName(userReq.getFirstName());
		updateUser.setLastName(userReq.getLastName());
		
		users.put(userId, updateUser);
		
		return updateUser;
	}

	@DeleteMapping
	public String deleteUser() {
		return "Delete called..";
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteUsers(@PathVariable String id) {
		users.remove(id);
		return ResponseEntity.noContent().build();
	}
}