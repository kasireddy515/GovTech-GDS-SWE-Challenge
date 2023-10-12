package com.govtech.assignment.controller.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.govtech.assignment.request.ChangePasswordRequest;
import com.govtech.assignment.request.ForgotPasswordRequest;
import com.govtech.assignment.request.ResetPasswordRequest;
import com.govtech.assignment.request.SignInRequest;
import com.govtech.assignment.response.AccountResponse;
import com.govtech.assignment.response.ChangePasswordResponse;
import com.govtech.assignment.response.ForgotPasswordResponse;
import com.govtech.assignment.response.ResetPasswordResponse;
import com.govtech.assignment.response.SignInResponse;
import com.govtech.assignment.service.AccountService;

@ExtendWith(SpringExtension.class)
public class AccountControllerImplTest {

	@InjectMocks
	private AccountControllerImpl controller;

	@Mock
	private AccountService service;

	@Test
	void test_SignInAccount() {
		SignInResponse signInResponse = new SignInResponse();
		signInResponse.setId("1234567890");
		when(service.signInAccount(any())).thenReturn(signInResponse);
		SignInRequest signInRequest = new SignInRequest();
		SignInResponse actualSignInResponse = controller.signInAccount(signInRequest).getBody();
		Assertions.assertEquals(signInResponse.getId(), actualSignInResponse.getId());
	}

	@Test
	void test_ForgotPassword() {
		ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
		forgotPasswordResponse.setId("1234567890");
		when(service.forgotPassword(any())).thenReturn(forgotPasswordResponse);
		ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
		ForgotPasswordResponse actualForgotPasswordResponse = controller.forgotPassword(forgotPasswordRequest).getBody();
		Assertions.assertEquals(forgotPasswordResponse.getId(), actualForgotPasswordResponse.getId());
	}

	@Test
	void test_ResetPassword() {
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
		resetPasswordResponse.setId("1234567890");
		when(service.resetPassword(any(), any())).thenReturn(resetPasswordResponse);
		ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
		ResetPasswordResponse actualResetPasswordResponse = controller.resetPassword("1212", resetPasswordRequest)
				.getBody();
		Assertions.assertEquals(resetPasswordResponse.getId(), actualResetPasswordResponse.getId());
	}

	@Test
	void test_ChangePassword() {
		ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
		changePasswordResponse.setId("1234567890");
		when(service.changePassword(any(), any())).thenReturn(changePasswordResponse);
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
		ChangePasswordResponse actualChangePasswordResponse = controller.changePassword("1212", changePasswordRequest)
				.getBody();
		Assertions.assertEquals(changePasswordResponse.getId(), actualChangePasswordResponse.getId());
	}

	@Test
	void test_getByAccountId() {
		AccountResponse accountResponse = new AccountResponse();
		accountResponse.setId("1234567890");
		when(service.getByAccountId(any())).thenReturn(accountResponse);
		AccountResponse actualAccountResponse = controller.getByAccountId("1212").getBody();
		Assertions.assertEquals(accountResponse.getId(), actualAccountResponse.getId());
	}
}
