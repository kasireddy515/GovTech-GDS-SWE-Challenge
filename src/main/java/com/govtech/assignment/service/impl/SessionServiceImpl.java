package com.govtech.assignment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.govtech.assignment.constant.AppConstants;
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
import com.govtech.assignment.service.SessionService;
import com.govtech.assignment.service.UserService;
import com.govtech.assignment.util.AccountUtil;
import com.govtech.assignment.util.CommonUtil;
import com.govtech.assignment.validator.SessionValidator;
import com.govtech.assignment.validator.UserValidator;

@Service
public class SessionServiceImpl implements SessionService {

	private final SessionRepository repository;
	private final SessionMapper mapper;
	private final SessionValidator validator;
	private final UserService userService;
	private final UserValidator userValidator;
	private final UserMapper userMapper;

	SessionServiceImpl(SessionRepository repository, SessionMapper mapper, SessionValidator validator,
			UserService userService, UserValidator userValidator, UserMapper userMapper) {
		this.repository = repository;
		this.mapper = mapper;
		this.validator = validator;
		this.userService = userService;
		this.userValidator = userValidator;
		this.userMapper = userMapper;
	}

	@Override
	public CreateSessionResponse create(CreateSessionRequest request) {

		Session session = validator.validateDuplicateSessionTitle(getByTitle(request.getTitle()));

		User user = userService.findById(AccountUtil.getLoggedInUserAccountInfo().getUserId());
		user = userValidator.validateInvalidUserId(user);

		session = mapper.mapCreateSessionRequestToEntity(request, user);

		if (request.getUserIds() != null && !request.getUserIds().isEmpty()) {
			session = inviteUsers(session, request.getUserIds());
		}

		repository.save(session);
		return mapper.mapEntityToCreateSessionResponse(session);
	}

	private Session getByTitle(String title) {
		return repository.findByTitleIgnoreCase(title);
	}

	@Override
	public Session findById(String id) {
		Optional<Session> optionalSession = repository.findById(id);
		return optionalSession.isPresent() ? optionalSession.get() : null;
	}

	@Override
	public SessionsResponse find(Integer pageOffset, Integer pageLimit, String searchText, String sortBy,
			String sortOrder) {

		SessionsResponse sessionsResponse = new SessionsResponse();

		Pageable paging = PageRequest.of(pageOffset, pageLimit, Direction.fromString(sortOrder.toUpperCase()), sortBy);

		Page<Session> pageSessinons = null;
		Long total = 0L;
		String userId = AccountUtil.getLoggedInUserAccountInfo().getUserId();

		if (searchText == null || searchText.isBlank() || searchText.equalsIgnoreCase(AppConstants.ALL)) {
			total = repository.countByUserId(userId);

		} else {
			total = repository.countByTitleAndUserId(searchText,userId);
		}

		if (total == 0) {
			sessionsResponse.setTotal(total);
			return sessionsResponse;
		}

		if (searchText == null || searchText.isBlank() || searchText.equalsIgnoreCase(AppConstants.ALL)) {
			pageSessinons = repository.findByUserId(paging, userId);
		} else {
			pageSessinons = repository.findByTitleAndUserId(searchText,userId, paging);
		}

		List<Session> sessions = pageSessinons != null && pageSessinons.hasContent() ? pageSessinons.getContent()
				: new ArrayList<>();

		List<SessionMetaDataResponse> sessionsResponses = mapper.modelsToDtos(sessions);
		sessionsResponse.setSessions(sessionsResponses);
		sessionsResponse.setTotal(total);

		return sessionsResponse;

	}

	@Override
	public SessionResponse getById(String id) {
		Session session = validator.validateInvalidSessionId(findById(id));
		return mapper.mapEntityToResponse(session);
	}

	@Override
	public CreateSessionInvitesResponse invite(String sessionId, CreateSessionInviteRequest request) {
		Session session = validator.validateInvalidSessionId(findById(sessionId));
		session = inviteUsers(session, request.getUserIds());
		session = repository.save(session);
		CreateSessionInvitesResponse createSessionInvitesResponse = new CreateSessionInvitesResponse();
		Set<String> sessionUserInviteIds = session.getUsersInvited().stream().map(user -> user.getId())
				.collect(Collectors.toSet());
		createSessionInvitesResponse.setSessionUserInviteIds(sessionUserInviteIds);
		return createSessionInvitesResponse;
	}

	private Session inviteUsers(Session session, Set<String> usersToBeInvited) {
		Set<User> newInvitedUsers = userService.findByIds(usersToBeInvited);
		Set<User> usersInvited = session.getUsersInvited();
		usersInvited.addAll(newInvitedUsers);
		session.setUsersInvited(usersInvited);
		return session;
	}

	@Override
	public UpdateSessionResponse update(String sessionId, UpdateSessionRequest request) {

		Session session = validator.validateInvalidSessionId(findById(sessionId));

		session.setTitle(request.getTitle() != null ? request.getTitle() : session.getTitle());
		session.setDescription(request.getDescription() != null ? request.getDescription() : session.getDescription());
		session.setActive(request.getActive() != null ? request.getActive() : session.getActive());

		Date instant = CommonUtil.getCurrentGMTDate();
		session.setUpdatedOn(instant);

		repository.save(session);
		return mapper.mapEntityToUpdateSessionResponse(session);
	}

	@Override
	public Boolean delete(String sessionId) {
		Session session = validator.validateInvalidSessionId(findById(sessionId));
		repository.delete(session);
		return Boolean.TRUE;
	}

	@Override
	public Set<UserResponse> getInvitees(String sessionId) {
		Session session = validator.validateInvalidSessionId(findById(sessionId));
		return userMapper.modelsToDtos(session.getUsersInvited());
	}

	@Override
	public void suggestRestaurantToSession(Restaurant restaurant, Session session) {
		Set<Restaurant> submittedRestaurants = session.getSubmittedRestaurants();
		submittedRestaurants.add(restaurant);
		session.setSubmittedRestaurants(submittedRestaurants);
		repository.save(session);
	}
}
