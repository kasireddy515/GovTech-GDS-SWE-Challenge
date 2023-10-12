package com.govtech.assignment.validator;

import org.springframework.stereotype.Component;

import com.govtech.assignment.constant.ErrorResponseConstants;
import com.govtech.assignment.entity.Session;
import com.govtech.assignment.exception.DataConflictException;
import com.govtech.assignment.exception.DataNotFoundException;

@Component
public class SessionValidator {

	public Session validateDuplicateSessionTitle(Session session) {

		if (session != null) {
			throw new DataConflictException(ErrorResponseConstants.SESSION_EXISTS_TITLE);
		}

		return session;
	}
	
	public Session validateInvalidSessionId(Session session) {

		if (session == null) {
			throw new DataNotFoundException(ErrorResponseConstants.SESSION_NOT_FOUND_ID);
		}

		return session;
	}

}
