package com.govtech.assignment.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.govtech.assignment.constant.ErrorResponseConstants;
import com.govtech.assignment.entity.Account;
import com.govtech.assignment.exception.DataNotFoundException;
import com.govtech.assignment.mapper.AccountMapper;
import com.govtech.assignment.repository.AccountRepository;
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
import com.govtech.assignment.security.token.JwtUtils;
import com.govtech.assignment.service.AccountService;
import com.govtech.assignment.util.CommonUtil;
import com.govtech.assignment.validator.AccountValidator;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository repository;
	private final AccountMapper mapper;
	private final AccountValidator validator;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	AccountServiceImpl(AccountRepository repository, AccountMapper mapper, AccountValidator validator,
			AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.repository = repository;
		this.mapper = mapper;
		this.validator = validator;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Account createAccount(CreateAccountRequest createAccountRequest) {

		Account account = validator.validateDuplicateUserName(getByUserName(createAccountRequest.getUserName()));

		account = mapper.mapToEntity(createAccountRequest);

		return repository.save(account);
	}

	@Override
	public SignInResponse signInAccount(SignInRequest request) {

		Account account = validator.validateInvalidUserName(getByUserName(request.getUserName()));

		String passwordHash = CommonUtil.hash(request.getPassword(), CommonUtil.decodeSalt(account.getPasswordSalt()));

		if (passwordHash == null || (!passwordHash.equals(account.getPasswordHash()))) {
			throw new DataNotFoundException(ErrorResponseConstants.INVALID_ACCOUNT_PASSWORD);
		}

		SignInResponse signInResponse = mapper.mapToSignInResponse(account);
		signInResponse.setAccessToken(getAuthenticationToken(account));
		
		return signInResponse;
	}

	@Override
	public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {

		Account account = validator.validateInvalidUserName(getByUserName(request.getUserName()));

		return mapper.mapToForgotPasswordResponse(account);

	}

	private Account getByUserName(String userName) {

		return repository.findByUserName(userName);

	}

	private Account getById(String id) {
		Optional<Account> optionalAccount = repository.findById(id);
		return optionalAccount.isPresent() ? optionalAccount.get() : null;

	}

	@Override
	public ResetPasswordResponse resetPassword(String accountId, ResetPasswordRequest request) {

		Account account = validator.validateInvalidAccountId(getById(accountId));

		account = validator.validateSamePassword(account, request.getPassword());

		account = mapper.changePassword(account, request.getPassword());

		account = repository.save(account);

		return mapper.mapToResetPasswordResponse(account);
	}

	@Override
	public ChangePasswordResponse changePassword(String accountId, ChangePasswordRequest request) {

		Account account = validator.validateInvalidAccountId(getById(accountId));

		account = validator.validateInvalidPassword(account, request);

		account = mapper.changePassword(account, request.getPassword());

		account = repository.save(account);

		return mapper.mapToChangePasswordResponse(account);
	}

	@Override
	public AccountResponse getByAccountId(String accountId) {

		Account account = validator.validateInvalidAccountId(getById(accountId));

		return mapper.mapToAccountResponse(account);
	}

	private String getAuthenticationToken(Account account) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				account.getUserName(), account.getPasswordHash() + "" + account.getPasswordSalt()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return jwtUtils.generateJwtToken(authentication);
	}
}
