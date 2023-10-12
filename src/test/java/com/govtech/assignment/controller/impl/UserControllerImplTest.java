package com.govtech.assignment.controller.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.govtech.assignment.request.CreateUserRequest;
import com.govtech.assignment.response.CreateUserResponse;
import com.govtech.assignment.response.UserResponse;
import com.govtech.assignment.response.UsersResponse;
import com.govtech.assignment.service.UserService;

@ExtendWith(SpringExtension.class)
public class UserControllerImplTest {

	@InjectMocks
	private UserControllerImpl controller;

	@Mock
	private UserService service;

	@Test
	void test_CreateUser() {
		CreateUserResponse createUserResponse = new CreateUserResponse();
		createUserResponse.setId("1234567890");
		when(service.create(any())).thenReturn(createUserResponse);
		CreateUserRequest createUserRequest = new CreateUserRequest();
		CreateUserResponse actualCreateUserResponse = controller.create(createUserRequest).getBody();
		Assertions.assertEquals(createUserResponse.getId(), actualCreateUserResponse.getId());
	}
	
	@Test
	void test_GetByAccountId() {
		UserResponse userResponse = new UserResponse();
		userResponse.setId("1234567890");
		when(service.getByAccountId(any())).thenReturn(userResponse);
		UserResponse actualUserResponse = controller.getByAccountId("1234567890").getBody();
		Assertions.assertEquals(userResponse.getId(), actualUserResponse.getId());
	}
	
	@Test
	void test_GetById() {
		UserResponse userResponse = new UserResponse();
		userResponse.setId("1234567890");
		when(service.getById(any())).thenReturn(userResponse);
		UserResponse actualUserResponse = controller.getById("1234567890").getBody();
		Assertions.assertEquals(userResponse.getId(), actualUserResponse.getId());
	}
	
	@Test
	void test_Find() {
		UsersResponse usersResponse = new UsersResponse();
		usersResponse.setTotal(10L);
		when(service.find(any(),any(),any(),any(),any())).thenReturn(usersResponse);
		UsersResponse actualUsersResponse = controller.find(0,10,"ALL","createdOn","ASC").getBody();
		Assertions.assertEquals(usersResponse.getTotal(), actualUsersResponse.getTotal());
	}
}
