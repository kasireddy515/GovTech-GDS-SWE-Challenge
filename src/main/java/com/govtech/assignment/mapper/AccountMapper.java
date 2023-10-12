package com.govtech.assignment.mapper;

import java.util.Date;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.govtech.assignment.entity.Account;
import com.govtech.assignment.request.CreateAccountRequest;
import com.govtech.assignment.response.AccountResponse;
import com.govtech.assignment.response.ChangePasswordResponse;
import com.govtech.assignment.response.ForgotPasswordResponse;
import com.govtech.assignment.response.ResetPasswordResponse;
import com.govtech.assignment.response.SignInResponse;
import com.govtech.assignment.util.CommonUtil;

@Mapper
public interface AccountMapper {

	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

	@Mapping(source = "userName", target = "userName")
	public Account mapToEntity(CreateAccountRequest createAccountRequest);

	@Mapping(source = "id", target = "id")
	public SignInResponse mapToSignInResponse(Account account);

	@Mapping(source = "id", target = "id")
	public ForgotPasswordResponse mapToForgotPasswordResponse(Account account);

	@Mapping(source = "id", target = "id")
	public ResetPasswordResponse mapToResetPasswordResponse(Account account);

	@Mapping(source = "id", target = "id")
	public ChangePasswordResponse mapToChangePasswordResponse(Account account);

	@AfterMapping
	default void populateAccountAuditInfo(CreateAccountRequest createAccountRequest, @MappingTarget Account account) {

		Date instant = CommonUtil.getCurrentGMTDate();

		account.setCreatedOn(instant);
		account.setUpdatedOn(instant);

		byte[] passwordSalt = CommonUtil.generateSalt();
		String passwordHash = CommonUtil.hash(createAccountRequest.getPassword(), passwordSalt);

		account.setPasswordSalt(CommonUtil.encodeSalt(passwordSalt));
		account.setPasswordHash(passwordHash);
		account.setId(CommonUtil.generateId());

	}

	default Account changePassword(Account account, String newPassword) {

		byte[] passwordSalt = CommonUtil.generateSalt();
		String passwordHash = CommonUtil.hash(newPassword, passwordSalt);

		account.setPasswordSalt(CommonUtil.encodeSalt(passwordSalt));
		account.setPasswordHash(passwordHash);

		Date currentDate = CommonUtil.getCurrentGMTDate();

		account.setUpdatedOn(currentDate);

		return account;

	}

	@Mapping(source = "id", target = "id")
	@Mapping(source = "userName", target = "userName")
	public AccountResponse mapToAccountResponse(Account account);
}
