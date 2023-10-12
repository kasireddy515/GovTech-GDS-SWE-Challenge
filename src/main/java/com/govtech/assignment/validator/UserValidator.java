package com.govtech.assignment.validator;

import org.springframework.stereotype.Component;

import com.govtech.assignment.constant.ErrorResponseConstants;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.exception.DataConflictException;
import com.govtech.assignment.exception.DataNotFoundException;

@Component
public class UserValidator {

	public User validateDuplicateUserName(User user) {

		if (user != null) {
			throw new DataConflictException(ErrorResponseConstants.USER_EXISTS_FIRST_LAST_NAME);
		}

		return user;
	}

	public User validateInvalidUserId(User user) {

		if (user == null) {
			throw new DataNotFoundException(ErrorResponseConstants.USER_NOT_FOUND_ID);
		}

		return user;
	}

	public User validateInvalidUserAccountId(User user) {

		if (user == null) {
			throw new DataNotFoundException(ErrorResponseConstants.USER_NOT_FOUND_ACCOUNT_ID);
		}

		return user;
	}
}
