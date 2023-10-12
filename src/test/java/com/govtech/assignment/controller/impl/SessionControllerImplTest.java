package com.govtech.assignment.controller.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

@ExtendWith(SpringExtension.class)
public class SessionControllerImplTest {

	@InjectMocks
	private SessionControllerImpl controller;

	@Mock
	private SessionService service;

	@Test
	void test_create() {
		CreateSessionResponse createSessionResponse = new CreateSessionResponse();
		createSessionResponse.setId("1234567890");
		when(service.create(any())).thenReturn(createSessionResponse);
		CreateSessionRequest createSessionRequest = new CreateSessionRequest();
		CreateSessionResponse actualCreateSessionResponse = controller.create(createSessionRequest).getBody();
		Assertions.assertEquals(createSessionResponse.getId(), actualCreateSessionResponse.getId());
	}

	@Test
	void test_find() {
		SessionsResponse sessionsResponse = new SessionsResponse();
		sessionsResponse.setTotal(10L);
		when(service.find(any(), any(), any(), any(), any())).thenReturn(sessionsResponse);
		SessionsResponse actualSessionsResponse = controller.find(0, 10, "ALL", "createdOn", "ASC").getBody();
		Assertions.assertEquals(sessionsResponse.getTotal(), actualSessionsResponse.getTotal());
	}

	@Test
	void test_getById() {
		SessionResponse sessionResponse = new SessionResponse();
		sessionResponse.setId("121212");
		when(service.getById(any())).thenReturn(sessionResponse);
		SessionResponse actualSessionResponse = controller.getById(sessionResponse.getId()).getBody();
		Assertions.assertEquals(sessionResponse.getId(), actualSessionResponse.getId());
	}

	@Test
	void test_invite() {
		CreateSessionInvitesResponse createSessionInvitesResponse = new CreateSessionInvitesResponse();
		Set<String> sessionUserInviteIds = new HashSet<>();
		sessionUserInviteIds.add("12345");
		createSessionInvitesResponse.setSessionUserInviteIds(sessionUserInviteIds);
		when(service.invite(any(), any())).thenReturn(createSessionInvitesResponse);
		CreateSessionInviteRequest createSessionInviteRequest = new CreateSessionInviteRequest();
		CreateSessionInvitesResponse actualCreateSessionInvitesResponse = controller
				.invite("12345", createSessionInviteRequest).getBody();
		Assertions.assertEquals(createSessionInvitesResponse.getSessionUserInviteIds().size(),
				actualCreateSessionInvitesResponse.getSessionUserInviteIds().size());
	}

	@Test
	void test_update() {
		UpdateSessionResponse updateSessionResponse = new UpdateSessionResponse();
		updateSessionResponse.setId("12345");
		when(service.update(any(), any())).thenReturn(updateSessionResponse);
		UpdateSessionRequest updateSessionRequest = new UpdateSessionRequest();
		UpdateSessionResponse actualUpdateSessionResponse = controller.update("12345", updateSessionRequest).getBody();
		Assertions.assertEquals(updateSessionResponse.getId(), actualUpdateSessionResponse.getId());
	}

	@Test
	void test_delete() {
		when(service.delete(any())).thenReturn(true);
		Boolean actualDeleteResponse = controller.delete("12345").getBody();
		Assertions.assertEquals(true, actualDeleteResponse);
	}

	@Test
	void test_getInvitees() {
		Set<UserResponse> usersResponse = new HashSet<>();
		UserResponse userResponse = new UserResponse();
		usersResponse.add(userResponse);
		when(service.getInvitees(any())).thenReturn(usersResponse);
		Set<UserResponse> actualInvitesResponse = controller.getInvitees("12345").getBody();
		Assertions.assertEquals(usersResponse.size(), actualInvitesResponse.size());
	}
}
