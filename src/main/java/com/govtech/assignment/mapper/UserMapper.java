package com.govtech.assignment.mapper;

import java.util.Date;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.govtech.assignment.entity.Account;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.request.CreateAccountRequest;
import com.govtech.assignment.request.CreateUserRequest;
import com.govtech.assignment.response.CreateUserResponse;
import com.govtech.assignment.response.UserResponse;
import com.govtech.assignment.util.CommonUtil;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "userName", target = "userName")
	@Mapping(source = "password", target = "password")
	CreateAccountRequest mapCreateUserRequestToCreateAccountRequest(CreateUserRequest request);

	@Mapping(source = "request.firstName", target = "firstName")
	@Mapping(source = "request.lastName", target = "lastName")
	@Mapping(source = "account", target = "account")
	User mapRequestToEntity(CreateUserRequest request, Account account);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "account.id", target = "accountId")
	CreateUserResponse mapEntityToCreateUserResponse(User user);

	@AfterMapping
	default void populateNewUserAuditInfo(CreateUserRequest request, Account account, @MappingTarget User user) {

		Date instant = CommonUtil.getCurrentGMTDate();

		user.setCreatedOn(instant);
		user.setUpdatedOn(instant);
		user.setId(CommonUtil.generateId());

	}

	@Mapping(source = "firstName", target = "firstName")
	@Mapping(source = "lastName", target = "lastName")
	@Mapping(source = "id", target = "id")
	UserResponse modelToDto(User user);

	Set<UserResponse> modelsToDtos(Set<User> users);
}
