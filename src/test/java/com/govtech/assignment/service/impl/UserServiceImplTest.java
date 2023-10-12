package com.govtech.assignment.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.govtech.assignment.entity.Account;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.mapper.UserMapper;
import com.govtech.assignment.repository.UserRepository;
import com.govtech.assignment.request.CreateAccountRequest;
import com.govtech.assignment.request.CreateUserRequest;
import com.govtech.assignment.response.CreateUserResponse;
import com.govtech.assignment.response.UserResponse;
import com.govtech.assignment.response.UsersResponse;
import com.govtech.assignment.service.AccountService;
import com.govtech.assignment.validator.UserValidator;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@Mock
	private UserMapper mapper;

	@Mock
	private AccountService accountService;

	@Mock
	private UserValidator validator;

	@Test
	void test_CreateUser() {

		CreateUserResponse createUserResponse = new CreateUserResponse();
		createUserResponse.setId("1234567890");
		CreateAccountRequest createAccountRequest = new CreateAccountRequest();

		when(repository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(any(), any())).thenReturn(new User());
		when(validator.validateDuplicateUserName(any())).thenReturn(new User());
		when(mapper.mapCreateUserRequestToCreateAccountRequest(any())).thenReturn(createAccountRequest);
		when(accountService.createAccount(any())).thenReturn(new Account());
		when(mapper.mapRequestToEntity(any(), any())).thenReturn(new User());
		when(repository.save(any())).thenReturn(new User());
		when(mapper.mapEntityToCreateUserResponse(any())).thenReturn(createUserResponse);

		CreateUserRequest createUserRequest = new CreateUserRequest();
		CreateUserResponse actualCreateUserResponse = service.create(createUserRequest);
		Assertions.assertEquals(createUserResponse.getId(), actualCreateUserResponse.getId());
	}

	@Test
	void test_GetByAccountId() {
		UserResponse userResponse = new UserResponse();

		when(repository.findByAccountId(any())).thenReturn(new User());
		when(validator.validateInvalidUserAccountId(any())).thenReturn(new User());
		when(mapper.modelToDto(any())).thenReturn(new UserResponse());

		UserResponse actualUserResponse = service.getByAccountId("1234567890");
		Assertions.assertEquals(userResponse.getId(), actualUserResponse.getId());
	}

	@Test
	void test_GetById_Empty() {
		UserResponse userResponse = new UserResponse();
		Optional<User> optionalUser = Optional.empty();
		when(repository.findById(any())).thenReturn(optionalUser);
		when(validator.validateInvalidUserId(any())).thenReturn(new User());
		when(mapper.modelToDto(any())).thenReturn(new UserResponse());

		UserResponse actualUserResponse = service.getById("1234567890");
		Assertions.assertEquals(userResponse.getId(), actualUserResponse.getId());
	}

	@Test
	void test_GetById() {
		UserResponse userResponse = new UserResponse();
		User user = new User();
		Optional<User> optionalUser = Optional.of(user);
		when(repository.findById(any())).thenReturn(optionalUser);
		when(validator.validateInvalidUserId(any())).thenReturn(new User());
		when(mapper.modelToDto(any())).thenReturn(new UserResponse());

		UserResponse actualUserResponse = service.getById("1234567890");
		Assertions.assertEquals(userResponse.getId(), actualUserResponse.getId());
	}

	@Test
	void test_Find_Search_Text_Null() {
		UsersResponse usersResponse = new UsersResponse();
		usersResponse.setTotal(10L);

		List<User> users = new ArrayList<>();
		User user = new User();
		users.add(user);
		Page<User> userPage = new PageImpl<>(users);

		when(repository.count()).thenReturn(10L);
		when(repository.findAll(any(Pageable.class))).thenReturn(userPage);

		Set<UserResponse> usersResponses = new HashSet<>();
		when(mapper.modelsToDtos(any())).thenReturn(usersResponses);
		UsersResponse actualUsersResponse = service.find(0, 10, null, "createdOn", "ASC");
		Assertions.assertEquals(usersResponse.getTotal(), actualUsersResponse.getTotal());
	}

	@Test
	void test_Find_Search_Text_Empty() {
		UsersResponse usersResponse = new UsersResponse();
		usersResponse.setTotal(10L);

		List<User> users = new ArrayList<>();
		User user = new User();
		users.add(user);
		Page<User> userPage = new PageImpl<>(users);

		when(repository.count()).thenReturn(10L);
		when(repository.findAll(any(Pageable.class))).thenReturn(userPage);

		Set<UserResponse> usersResponses = new HashSet<>();
		when(mapper.modelsToDtos(any())).thenReturn(usersResponses);
		UsersResponse actualUsersResponse = service.find(0, 10, "", "createdOn", "ASC");
		Assertions.assertEquals(usersResponse.getTotal(), actualUsersResponse.getTotal());
	}

	@Test
	void test_Find_Search_Text_ALL() {
		UsersResponse usersResponse = new UsersResponse();
		usersResponse.setTotal(10L);

		List<User> users = new ArrayList<>();
		User user = new User();
		users.add(user);
		Page<User> userPage = new PageImpl<>(users);

		when(repository.count()).thenReturn(10L);
		when(repository.findAll(any(Pageable.class))).thenReturn(userPage);

		Set<UserResponse> usersResponses = new HashSet<>();
		when(mapper.modelsToDtos(any())).thenReturn(usersResponses);
		UsersResponse actualUsersResponse = service.find(0, 10, "ALL", "createdOn", "ASC");
		Assertions.assertEquals(usersResponse.getTotal(), actualUsersResponse.getTotal());
	}

	@Test
	void test_Find_No_Results() {
		UsersResponse usersResponse = new UsersResponse();
		usersResponse.setTotal(0L);
		when(repository.count()).thenReturn(0L);
		UsersResponse actualUsersResponse = service.find(0, 10, "ALL", "createdOn", "ASC");
		Assertions.assertEquals(usersResponse.getTotal(), actualUsersResponse.getTotal());
	}

	@Test
	void test_Find_Search_Text_Valid() {
		UsersResponse usersResponse = new UsersResponse();
		usersResponse.setTotal(10L);

		List<User> users = new ArrayList<>();
		User user = new User();
		users.add(user);
		Page<User> userPage = new PageImpl<>(users);

		when(repository.countByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(any(), any()))
				.thenReturn(10L);
		when(repository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(any(), any(), any()))
				.thenReturn(userPage);

		Set<UserResponse> usersResponses = new HashSet<>();
		when(mapper.modelsToDtos(any())).thenReturn(usersResponses);
		UsersResponse actualUsersResponse = service.find(0, 10, "GovTech", "createdOn", "ASC");
		Assertions.assertEquals(usersResponse.getTotal(), actualUsersResponse.getTotal());
	}

	@Test
	void test_FindByIds() {
		Set<User> usersResponse = new HashSet<>();
		User userResponse = new User();
		usersResponse.add(userResponse);
		when(repository.findByIdIn(any())).thenReturn(usersResponse);

		Set<User> actualUsersResponse = service.findByIds(new HashSet<>());
		Assertions.assertEquals(usersResponse.size(), actualUsersResponse.size());
	}
}
