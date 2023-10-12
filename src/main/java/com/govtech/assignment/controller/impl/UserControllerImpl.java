package com.govtech.assignment.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.govtech.assignment.controller.UserController;
import com.govtech.assignment.request.CreateUserRequest;
import com.govtech.assignment.response.CreateUserResponse;
import com.govtech.assignment.response.UserResponse;
import com.govtech.assignment.response.UsersResponse;
import com.govtech.assignment.service.UserService;

@RestController
public class UserControllerImpl implements UserController {

	private final UserService service;

	UserControllerImpl(UserService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<CreateUserResponse> create(CreateUserRequest request) {
		return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserResponse> getByAccountId(String accountId) {
		return new ResponseEntity<>(service.getByAccountId(accountId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> getById(String id) {
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsersResponse> find(Integer pageOffset, Integer pageLimit, String searchText, String sortBy,
			String sortOrder) {
		return new ResponseEntity<>(service.find(pageOffset, pageLimit, searchText, sortBy, sortOrder), HttpStatus.OK);
	}

}
