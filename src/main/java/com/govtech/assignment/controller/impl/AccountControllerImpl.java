package com.govtech.assignment.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.govtech.assignment.controller.AccountController;
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

@RestController
public class AccountControllerImpl implements AccountController {

	private final AccountService service;

	AccountControllerImpl(AccountService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<SignInResponse> signInAccount(SignInRequest request) {
		return new ResponseEntity<>(service.signInAccount(request), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ForgotPasswordResponse> forgotPassword(ForgotPasswordRequest request) {
		return new ResponseEntity<>(service.forgotPassword(request), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResetPasswordResponse> resetPassword(String accountId, ResetPasswordRequest request) {
		return new ResponseEntity<>(service.resetPassword(accountId, request), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ChangePasswordResponse> changePassword(String accountId, ChangePasswordRequest request) {
		return new ResponseEntity<>(service.changePassword(accountId, request), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AccountResponse> getByAccountId(String id) {
		return new ResponseEntity<>(service.getByAccountId(id), HttpStatus.OK);
	}
}
