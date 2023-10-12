package com.govtech.assignment.service;

import java.util.Set;

import com.govtech.assignment.entity.User;
import com.govtech.assignment.request.CreateUserRequest;
import com.govtech.assignment.response.CreateUserResponse;
import com.govtech.assignment.response.UserResponse;
import com.govtech.assignment.response.UsersResponse;

public interface UserService {

	CreateUserResponse create(CreateUserRequest request);

	User findById(String id);

	UserResponse getByAccountId(String accountId);

	UserResponse getById(String id);

	UsersResponse find(Integer pageOffset, Integer pageLimit, String searchText, String sortBy, String sortOrder);

	Set<User> findByIds(Set<String> userIds);

}
