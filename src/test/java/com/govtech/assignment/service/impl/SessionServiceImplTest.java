package com.govtech.assignment.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.govtech.assignment.entity.Restaurant;
import com.govtech.assignment.entity.Session;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.mapper.SessionMapper;
import com.govtech.assignment.mapper.UserMapper;
import com.govtech.assignment.repository.SessionRepository;
import com.govtech.assignment.request.CreateSessionInviteRequest;
import com.govtech.assignment.request.CreateSessionRequest;
import com.govtech.assignment.request.UpdateSessionRequest;
import com.govtech.assignment.response.CreateSessionInvitesResponse;
import com.govtech.assignment.response.CreateSessionResponse;
import com.govtech.assignment.response.SessionMetaDataResponse;
import com.govtech.assignment.response.SessionResponse;
import com.govtech.assignment.response.SessionsResponse;
import com.govtech.assignment.response.UpdateSessionResponse;
import com.govtech.assignment.response.UserResponse;
import com.govtech.assignment.service.UserService;
import com.govtech.assignment.validator.SessionValidator;
import com.govtech.assignment.validator.UserValidator;

@ExtendWith(SpringExtension.class)
public class SessionServiceImplTest {

	@InjectMocks
	private SessionServiceImpl service;

	@Mock
	private SessionRepository repository;

	@Mock
	private SessionMapper mapper;

	@Mock
	private SessionValidator validator;

	@Mock
	private UserService userService;

	@Mock
	private UserValidator userValidator;

	@Mock
	private UserMapper userMapper;

	@Test
	void test_create() {
		CreateSessionResponse createSessionResponse = new CreateSessionResponse();
		createSessionResponse.setId("1234567890");

		Session session = new Session();

		when(validator.validateDuplicateSessionTitle(any())).thenReturn(session);
		when(repository.findByTitleIgnoreCase(any())).thenReturn(session);
		when(userService.findById(any())).thenReturn(new User());
		when(userValidator.validateInvalidUserId(any())).thenReturn(new User());

		when(mapper.mapCreateSessionRequestToEntity(any(), any())).thenReturn(session);

		when(repository.save(any())).thenReturn(session);
		when(mapper.mapEntityToCreateSessionResponse(any())).thenReturn(createSessionResponse);

		CreateSessionRequest createSessionRequest = new CreateSessionRequest();
		CreateSessionResponse actualCreateSessionResponse = service.create(createSessionRequest);
		Assertions.assertEquals(createSessionResponse.getId(), actualCreateSessionResponse.getId());
	}

	@Test
	void test_create_with_empty_invitees() {
		CreateSessionResponse createSessionResponse = new CreateSessionResponse();
		createSessionResponse.setId("1234567890");

		Session session = new Session();

		when(validator.validateDuplicateSessionTitle(any())).thenReturn(session);
		when(repository.findByTitleIgnoreCase(any())).thenReturn(session);
		when(userService.findById(any())).thenReturn(new User());
		when(userValidator.validateInvalidUserId(any())).thenReturn(new User());

		when(mapper.mapCreateSessionRequestToEntity(any(), any())).thenReturn(session);

		when(repository.save(any())).thenReturn(session);
		when(mapper.mapEntityToCreateSessionResponse(any())).thenReturn(createSessionResponse);

		CreateSessionRequest createSessionRequest = new CreateSessionRequest();
		Set<String> userIds = new HashSet<>();
		createSessionRequest.setUserIds(userIds);
		CreateSessionResponse actualCreateSessionResponse = service.create(createSessionRequest);
		Assertions.assertEquals(createSessionResponse.getId(), actualCreateSessionResponse.getId());
	}

	@Test
	void test_create_with_invitees() {
		CreateSessionResponse createSessionResponse = new CreateSessionResponse();
		createSessionResponse.setId("1234567890");

		Session session = new Session();

		when(validator.validateDuplicateSessionTitle(any())).thenReturn(session);
		when(repository.findByTitleIgnoreCase(any())).thenReturn(session);

		Set<User> newInvitedUsers = new HashSet<>();
		when(userService.findByIds(any())).thenReturn(newInvitedUsers);

		when(userService.findById(any())).thenReturn(new User());
		when(userValidator.validateInvalidUserId(any())).thenReturn(new User());

		when(mapper.mapCreateSessionRequestToEntity(any(), any())).thenReturn(session);

		when(repository.save(any())).thenReturn(session);
		when(mapper.mapEntityToCreateSessionResponse(any())).thenReturn(createSessionResponse);

		CreateSessionRequest createSessionRequest = new CreateSessionRequest();
		Set<String> userIds = new HashSet<>();
		userIds.add("1212");
		createSessionRequest.setUserIds(userIds);
		CreateSessionResponse actualCreateSessionResponse = service.create(createSessionRequest);
		Assertions.assertEquals(createSessionResponse.getId(), actualCreateSessionResponse.getId());
	}

	@Test
	void test_Find_Search_Text_Null() {
		SessionsResponse sessionsResponse = new SessionsResponse();
		sessionsResponse.setTotal(10L);

		List<Session> users = new ArrayList<>();
		Session user = new Session();
		users.add(user);
		Page<Session> userPage = new PageImpl<>(users);

		when(repository.countByUserId(any())).thenReturn(10L);
		when(repository.findByUserId(any(),any())).thenReturn(userPage);

		List<SessionMetaDataResponse> usersResponses = new ArrayList<>();
		when(mapper.modelsToDtos(any())).thenReturn(usersResponses);
		SessionsResponse actualSessionsResponse = service.find(0, 10, null, "createdOn", "ASC");
		Assertions.assertEquals(sessionsResponse.getTotal(), actualSessionsResponse.getTotal());
	}

	@Test
	void test_Find_Search_Text_Empty() {
		SessionsResponse sessionsResponse = new SessionsResponse();
		sessionsResponse.setTotal(10L);

		List<Session> users = new ArrayList<>();
		Session user = new Session();
		users.add(user);
		Page<Session> userPage = new PageImpl<>(users);

		when(repository.countByUserId(any())).thenReturn(10L);
		when(repository.findByUserId(any(),any())).thenReturn(userPage);

		List<SessionMetaDataResponse> usersResponses = new ArrayList<>();
		when(mapper.modelsToDtos(any())).thenReturn(usersResponses);
		SessionsResponse actualSessionsResponse = service.find(0, 10, "", "createdOn", "ASC");
		Assertions.assertEquals(sessionsResponse.getTotal(), actualSessionsResponse.getTotal());
	}

	@Test
	void test_Find_Search_Text_ALL() {
		SessionsResponse sessionsResponse = new SessionsResponse();
		sessionsResponse.setTotal(10L);

		List<Session> users = new ArrayList<>();
		Session user = new Session();
		users.add(user);
		Page<Session> userPage = new PageImpl<>(users);

		when(repository.countByUserId(any())).thenReturn(10L);
		when(repository.findByUserId(any(),any())).thenReturn(userPage);

		List<SessionMetaDataResponse> usersResponses = new ArrayList<>();
		when(mapper.modelsToDtos(any())).thenReturn(usersResponses);
		SessionsResponse actualSessionsResponse = service.find(0, 10, null, "ALL", "ASC");
		Assertions.assertEquals(sessionsResponse.getTotal(), actualSessionsResponse.getTotal());
	}

	@Test
	void test_Find_No_Results() {
		SessionsResponse usersResponse = new SessionsResponse();
		usersResponse.setTotal(0L);
		when(repository.countByUserId(any())).thenReturn(0L);
		SessionsResponse actualUsersResponse = service.find(0, 10, "ALL", "createdOn", "ASC");
		Assertions.assertEquals(usersResponse.getTotal(), actualUsersResponse.getTotal());
	}

	@Test
	void test_Find_Search_Text_Valid() {

		SessionsResponse sessionsResponse = new SessionsResponse();
		sessionsResponse.setTotal(10L);

		List<Session> users = new ArrayList<>();
		Session user = new Session();
		users.add(user);
		Page<Session> userPage = new PageImpl<>(users);

		when(repository.countByTitleAndUserId(any(),any())).thenReturn(10L);
		when(repository.findByTitleAndUserId(any(), any(), any())).thenReturn(userPage);

		List<SessionMetaDataResponse> usersResponses = new ArrayList<>();
		when(mapper.modelsToDtos(any())).thenReturn(usersResponses);
		SessionsResponse actualSessionsResponse = service.find(0, 10, "GovTech", "ALL", "ASC");
		Assertions.assertEquals(sessionsResponse.getTotal(), actualSessionsResponse.getTotal());
	}

	@Test
	void test_getById() {
		SessionResponse sessionResponse = new SessionResponse();
		sessionResponse.setId("121212");
		Session session = new Session();
		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(mapper.mapEntityToResponse(session)).thenReturn(sessionResponse);
		SessionResponse actualSessionResponse = service.getById(sessionResponse.getId());
		Assertions.assertEquals(sessionResponse.getId(), actualSessionResponse.getId());
	}

	@Test
	void test_invite() {
		CreateSessionInvitesResponse createSessionInvitesResponse = new CreateSessionInvitesResponse();
		Set<String> sessionUserInviteIds = new HashSet<>();
		sessionUserInviteIds.add("12345");
		createSessionInvitesResponse.setSessionUserInviteIds(sessionUserInviteIds);
		Session session = new Session();
		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(validator.validateInvalidOwnerSessionId(any(),any())).thenReturn(session);
		Set<User> users = new HashSet<>();
		User user = new User();
		users.add(user);
		when(userService.findByIds(any())).thenReturn(users);
		when(repository.save(any())).thenReturn(session);
		CreateSessionInviteRequest createSessionInviteRequest = new CreateSessionInviteRequest();
		CreateSessionInvitesResponse actualCreateSessionInvitesResponse = service.invite("12345",
				createSessionInviteRequest);
		Assertions.assertEquals(createSessionInvitesResponse.getSessionUserInviteIds().size(),
				actualCreateSessionInvitesResponse.getSessionUserInviteIds().size());
	}

	@Test
	void test_update() {
		UpdateSessionResponse updateSessionResponse = new UpdateSessionResponse();
		updateSessionResponse.setId("12345");
		Session session = new Session();
		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(validator.validateInvalidOwnerSessionId(any(),any())).thenReturn(session);
		when(repository.save(any())).thenReturn(session);
		when(mapper.mapEntityToUpdateSessionResponse(any())).thenReturn(updateSessionResponse);
		UpdateSessionRequest updateSessionRequest = new UpdateSessionRequest();
		UpdateSessionResponse actualUpdateSessionResponse = service.update("12345", updateSessionRequest);
		Assertions.assertEquals(updateSessionResponse.getId(), actualUpdateSessionResponse.getId());
	}

	@Test
	void test_getInvitees() {
		Set<UserResponse> usersResponse = new HashSet<>();
		UserResponse userResponse = new UserResponse();
		usersResponse.add(userResponse);
		Session session = new Session();
		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(userMapper.modelsToDtos(any())).thenReturn(usersResponse);
		Set<UserResponse> actualInvitesResponse = service.getInvitees("12345");
		Assertions.assertEquals(usersResponse.size(), actualInvitesResponse.size());
	}
	
	@Test
	void test_suggestRestaurantToSession() {
		Session session = new Session();
		when(repository.save(any())).thenReturn(session);
		service.suggestRestaurantToSession(new Restaurant(), new Session());
	}
}
