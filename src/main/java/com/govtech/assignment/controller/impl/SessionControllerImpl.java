package com.govtech.assignment.controller.impl;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.govtech.assignment.controller.SessionController;
import com.govtech.assignment.request.CreateSessionInviteRequest;
import com.govtech.assignment.request.CreateSessionRequest;
import com.govtech.assignment.request.UpdateSessionRequest;
import com.govtech.assignment.response.CreateSessionInvitesResponse;
import com.govtech.assignment.response.CreateSessionResponse;
import com.govtech.assignment.response.SessionResponse;
import com.govtech.assignment.response.SessionsResponse;
import com.govtech.assignment.response.UpdateSessionResponse;
import com.govtech.assignment.response.UserResponse;
import com.govtech.assignment.service.SessionService;

@RestController
public class SessionControllerImpl implements SessionController {

	private final SessionService service;

	SessionControllerImpl(SessionService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<CreateSessionResponse> create(CreateSessionRequest request) {
		return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<SessionsResponse> find(Integer pageOffset, Integer pageLimit, String searchText,
			String sortBy, String sortOrder) {
		return new ResponseEntity<>(service.find(pageOffset, pageLimit, searchText, sortBy, sortOrder), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SessionResponse> getById(String id) {
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CreateSessionInvitesResponse> invite(String sessionId,
			@Valid CreateSessionInviteRequest request) {
		return new ResponseEntity<>(service.invite(sessionId,request), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UpdateSessionResponse> update(String id, UpdateSessionRequest request) {
		return new ResponseEntity<>(service.update(id,request), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> delete(String id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<UserResponse>> getInvitees(String id) {
		return new ResponseEntity<>(service.getInvitees(id), HttpStatus.OK);
	}
}
