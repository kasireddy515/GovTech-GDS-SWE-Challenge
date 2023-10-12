package com.govtech.assignment.service;

import com.govtech.assignment.entity.Account;
import com.govtech.assignment.request.ChangePasswordRequest;
import com.govtech.assignment.request.CreateAccountRequest;
import com.govtech.assignment.request.ForgotPasswordRequest;
import com.govtech.assignment.request.ResetPasswordRequest;
import com.govtech.assignment.request.SignInRequest;
import com.govtech.assignment.response.AccountResponse;
import com.govtech.assignment.response.ChangePasswordResponse;
import com.govtech.assignment.response.ForgotPasswordResponse;
import com.govtech.assignment.response.ResetPasswordResponse;
import com.govtech.assignment.response.SignInResponse;

public interface AccountService {

	Account createAccount(CreateAccountRequest createAccountRequest);

	SignInResponse signInAccount(SignInRequest request);

	ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);

	ResetPasswordResponse resetPassword(String accountId, ResetPasswordRequest request);

	ChangePasswordResponse changePassword(String accountId, ChangePasswordRequest request);

	AccountResponse getByAccountId(String accountId);

}
