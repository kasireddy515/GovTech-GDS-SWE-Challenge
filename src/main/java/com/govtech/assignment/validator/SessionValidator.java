package com.govtech.assignment.validator;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.govtech.assignment.constant.ErrorResponseConstants;
import com.govtech.assignment.entity.Session;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.exception.DataConflictException;
import com.govtech.assignment.exception.DataNotFoundException;
import com.govtech.assignment.exception.UnAuthorizedException;

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

	public Session validateInvalidOwnerSessionId(Session session, String userId) {

		if (!session.getCreatedBy().getId().equals(userId)) {
			throw new UnAuthorizedException(ErrorResponseConstants.SESSION_NOT_OWNER_ID);
		}

		return session;
	}

	public Session validateInvalidInviteeSessionId(Session session, String userId) {

		if (session.getUsersInvited() == null || session.getUsersInvited().isEmpty()
				|| !isUserInvited(session, userId)) {

			throw new UnAuthorizedException(ErrorResponseConstants.SESSION_NOT_OWNER_ID);
		}

		return session;
	}

	private Boolean isUserInvited(Session session, String userId) {

		Set<User> usersInvited = session.getUsersInvited();
		Set<String> userIdsInvited = usersInvited.stream().map(u -> u.getId()).collect(Collectors.toSet());

		if (!session.getCreatedBy().getId().equals(userId) && !userIdsInvited.contains(userId)) {
			return false;
		}

		return true;

	}

}
