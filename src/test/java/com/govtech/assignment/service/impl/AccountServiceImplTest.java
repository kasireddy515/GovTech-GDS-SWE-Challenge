package com.govtech.assignment.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.govtech.assignment.entity.Account;
import com.govtech.assignment.mapper.AccountMapper;
import com.govtech.assignment.repository.AccountRepository;
import com.govtech.assignment.request.ChangePasswordRequest;
import com.govtech.assignment.request.CreateAccountRequest;
import com.govtech.assignment.request.ForgotPasswordRequest;
import com.govtech.assignment.request.ResetPasswordRequest;
import com.govtech.assignment.response.AccountResponse;
import com.govtech.assignment.response.ChangePasswordResponse;
import com.govtech.assignment.response.ForgotPasswordResponse;
import com.govtech.assignment.response.ResetPasswordResponse;
import com.govtech.assignment.security.token.JwtUtils;
import com.govtech.assignment.validator.AccountValidator;

@ExtendWith(SpringExtension.class)
public class AccountServiceImplTest {

	@InjectMocks
	private AccountServiceImpl service;

	@Mock
	private AccountRepository repository;
	@Mock
	private AccountMapper mapper;
	@Mock
	private AccountValidator validator;
	@Mock
	private AuthenticationManager authenticationManager;
	@Mock
	private JwtUtils jwtUtils;

	@Test
	void test_createAccount() {
		Account account = new Account();
		account.setId("1234567890");
		when(validator.validateDuplicateUserName(any())).thenReturn(account);
		when(mapper.mapToEntity(any())).thenReturn(account);
		when(repository.save(any())).thenReturn(account);
		CreateAccountRequest createAccountRequest = new CreateAccountRequest();
		Account actualAccount = service.createAccount(createAccountRequest);
		Assertions.assertEquals(account.getId(), actualAccount.getId());
	}
	
	@Test
	void test_ForgotPassword() {
		ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
		forgotPasswordResponse.setId("1234567890");
		Account account= new Account();
		when(validator.validateInvalidUserName(any())).thenReturn(account);
		when(mapper.mapToForgotPasswordResponse(any())).thenReturn(forgotPasswordResponse);
		ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
		ForgotPasswordResponse actualForgotPasswordResponse = service.forgotPassword(forgotPasswordRequest);
		Assertions.assertEquals(forgotPasswordResponse.getId(), actualForgotPasswordResponse.getId());
	}

	@Test
	void test_ResetPassword() {
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
		resetPasswordResponse.setId("1234567890");
		Account account= new Account();
		when(validator.validateInvalidAccountId(any())).thenReturn(account);
		when(validator.validateSamePassword(any(),any())).thenReturn(account);
		when(mapper.changePassword(any(),any())).thenReturn(account);
		when(repository.save(any())).thenReturn(account);
		when(mapper.mapToResetPasswordResponse(any())).thenReturn(resetPasswordResponse);
		ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
		ResetPasswordResponse actualResetPasswordResponse = service.resetPassword("1212", resetPasswordRequest);
		Assertions.assertEquals(resetPasswordResponse.getId(), actualResetPasswordResponse.getId());
	}

	@Test
	void test_ChangePassword() {
		ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
		changePasswordResponse.setId("1234567890");
		Account account= new Account();
		when(validator.validateInvalidAccountId(any())).thenReturn(account);
		when(validator.validateInvalidPassword(any(),any())).thenReturn(account);
		when(mapper.changePassword(any(),any())).thenReturn(account);
		when(repository.save(any())).thenReturn(account);
		when(mapper.mapToChangePasswordResponse(any())).thenReturn(changePasswordResponse);
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
		ChangePasswordResponse actualChangePasswordResponse = service.changePassword("1212", changePasswordRequest);
		Assertions.assertEquals(changePasswordResponse.getId(), actualChangePasswordResponse.getId());
	}

	@Test
	void test_getByAccountId() {
		AccountResponse accountResponse = new AccountResponse();
		accountResponse.setId("1234567890");
		Account account= new Account();
		when(validator.validateInvalidAccountId(any())).thenReturn(account);
		when(mapper.mapToAccountResponse(any())).thenReturn(accountResponse);
		AccountResponse actualAccountResponse = service.getByAccountId("1212");
		Assertions.assertEquals(accountResponse.getId(), actualAccountResponse.getId());
	}

}
