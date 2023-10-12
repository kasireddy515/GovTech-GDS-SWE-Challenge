package com.govtech.assignment.validator;

import org.springframework.stereotype.Component;

import com.govtech.assignment.constant.ErrorResponseConstants;
import com.govtech.assignment.entity.Account;
import com.govtech.assignment.exception.DataNotFoundException;
import com.govtech.assignment.request.ChangePasswordRequest;
import com.govtech.assignment.util.CommonUtil;

@Component
public class AccountValidator {

	public Account validateInvalidUserName(Account account) {

		if (account == null) {
			throw new DataNotFoundException(ErrorResponseConstants.ACCOUNT_NOT_FOUND_USER_NAME);
		}

		return account;
	}

	public Account validateDuplicateUserName(Account account) {

		if (account != null) {
			throw new DataNotFoundException(ErrorResponseConstants.ACCOUNT_EXISTS_FIRST_LAST_NAME);
		}

		return account;
	}

	public Account validateInvalidAccountId(Account account) {

		if (account == null) {
			throw new DataNotFoundException(ErrorResponseConstants.ACCOUNT_NOT_FOUND_ID);
		}

		return account;
	}

	public Account validateSamePassword(Account account, String password) {

		String passwordHash = CommonUtil.hash(password, CommonUtil.decodeSalt(account.getPasswordSalt()));

		if (passwordHash != null && passwordHash.equals(account.getPasswordHash())) {
			throw new DataNotFoundException(ErrorResponseConstants.INVALID_ACCOUNT_NEW_PASSWORD);
		}

		return account;

	}

	public Account validateInvalidPassword(Account account, ChangePasswordRequest request) {

		String passwordHash = CommonUtil.hash(request.getOldPassword(),
				CommonUtil.decodeSalt(account.getPasswordSalt()));

		if (passwordHash == null || (!passwordHash.equals(account.getPasswordHash()))) {
			throw new DataNotFoundException(ErrorResponseConstants.INVALID_ACCOUNT_OLD_PASSWORD);
		}

		return account;
	}

}
