package com.govtech.assignment.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.govtech.assignment.constant.AppConstants;
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
import com.govtech.assignment.service.UserService;
import com.govtech.assignment.validator.UserValidator;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final UserMapper mapper;
	private final AccountService accountService;
	private final UserValidator validator;

	UserServiceImpl(UserRepository repository, UserMapper mapper, AccountService accountService,
			UserValidator validator) {
		this.repository = repository;
		this.mapper = mapper;
		this.accountService = accountService;
		this.validator = validator;
	}

	@Override
	public CreateUserResponse create(CreateUserRequest request) {

		User user = validator.validateDuplicateUserName(getByName(request.getFirstName(), request.getLastName()));

		CreateAccountRequest createAccountRequest = mapper.mapCreateUserRequestToCreateAccountRequest(request);

		Account account = accountService.createAccount(createAccountRequest);

		user = mapper.mapRequestToEntity(request, account);

		user = repository.save(user);

		return mapper.mapEntityToCreateUserResponse(user);
	}

	private User getByName(String firstName, String lastName) {

		return repository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);

	}

	@Override
	public User findById(String id) {
		Optional<User> optionalUser = repository.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;

	}

	@Override
	public UserResponse getByAccountId(String accountId) {

		User user = repository.findByAccountId(accountId);
		user = validator.validateInvalidUserAccountId(user);

		return mapper.modelToDto(user);
	}

	@Override
	public UserResponse getById(String id) {

		User user = findById(id);
		user = validator.validateInvalidUserId(user);

		return mapper.modelToDto(user);
	}

	@Override
	public UsersResponse find(Integer pageOffset, Integer pageLimit, String searchText, String sortBy,
			String sortOrder) {

		UsersResponse usersResponse = new UsersResponse();

		Pageable paging = PageRequest.of(pageOffset, pageLimit, Direction.fromString(sortOrder.toUpperCase()), sortBy);

		Page<User> pageUsers = null;
		Long total = 0L;

		if (searchText == null || searchText.isBlank() || searchText.equalsIgnoreCase(AppConstants.ALL)) {
			total = repository.count();

		} else {
			total = repository.countByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchText,
					searchText);
		}

		if (total == 0) {
			usersResponse.setTotal(total);
			return usersResponse;
		}

		if (searchText == null || searchText.isBlank() || searchText.equalsIgnoreCase(AppConstants.ALL)) {
			pageUsers = repository.findAll(paging);
		} else {
			pageUsers = repository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchText,
					searchText, paging);
		}

		Set<UserResponse> usersResponses = mapper.modelsToDtos(new HashSet<>(pageUsers.getContent()));
		usersResponse.setUsers(usersResponses);
		usersResponse.setTotal(total);

		return usersResponse;

	}

	@Override
	public Set<User> findByIds(Set<String> userIds) {
		return repository.findByIdIn(userIds);
	}
}
