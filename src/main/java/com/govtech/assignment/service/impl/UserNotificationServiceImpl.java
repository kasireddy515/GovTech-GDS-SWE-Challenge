package com.govtech.assignment.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.govtech.assignment.constant.ErrorResponseConstants;
import com.govtech.assignment.dto.CreateUserNotificationDTO;
import com.govtech.assignment.entity.Session;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.entity.UserNotification;
import com.govtech.assignment.exception.DataNotFoundException;
import com.govtech.assignment.mapper.UserNotificationMapper;
import com.govtech.assignment.repository.UserNotificationRepository;
import com.govtech.assignment.request.CreateUserNotificationRequest;
import com.govtech.assignment.request.UpdateUserNotificationRequest;
import com.govtech.assignment.response.CreateUserNotificationResponse;
import com.govtech.assignment.response.UpdateUserNotificationResponse;
import com.govtech.assignment.response.UserNotificationResponse;
import com.govtech.assignment.service.SessionService;
import com.govtech.assignment.service.UserNotificationService;
import com.govtech.assignment.service.UserService;
import com.govtech.assignment.util.AccountUtil;
import com.govtech.assignment.validator.SessionValidator;
import com.govtech.assignment.validator.UserValidator;

@Service
public class UserNotificationServiceImpl implements UserNotificationService {

	private final UserNotificationRepository repository;
	private final UserNotificationMapper mapper;
	private final SessionValidator validator;
	private final UserService userService;
	private final SessionService sessionService;
	private final UserValidator userValidator;

	private final List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());

	UserNotificationServiceImpl(UserNotificationRepository repository, UserNotificationMapper mapper,
			final SessionValidator validator, UserService userService, SessionService sessionService,
			UserValidator userValidator) {
		this.repository = repository;
		this.mapper = mapper;
		this.validator = validator;
		this.userService = userService;
		this.sessionService = sessionService;
		this.userValidator = userValidator;
	}

	@Override
	public List<CreateUserNotificationResponse> create(String sessionId,
			CreateUserNotificationRequest createUserNotificationRequest) {

		Session session = validator.validateInvalidSessionId(sessionService.findById(sessionId));

		User senderUser = userService.findById(AccountUtil.getLoggedInUserAccountInfo().getUserId());
		senderUser = userValidator.validateInvalidUserId(senderUser);

		Set<User> receiverUsers = userService.findByIds(createUserNotificationRequest.getUserIds());

		List<UserNotification> userNotifications = new ArrayList<>();
		List<CreateUserNotificationDTO> createUserNotificationDTOs = new ArrayList<>();

		if (receiverUsers != null && !receiverUsers.isEmpty()) {

			for (User receiverUser : receiverUsers) {
				CreateUserNotificationDTO createUserNotificationDTO = mapper.mapCreateUserNotificationRequest(
						createUserNotificationRequest, senderUser, receiverUser, session);
				createUserNotificationDTOs.add(createUserNotificationDTO);
			}

			userNotifications = mapper.mapDTOsToEntities(createUserNotificationDTOs);

			userNotifications = repository.saveAll(userNotifications);

			List<UserNotificationResponse> userNotificationsResponse = mapper.mapEntitiesToDTOs(userNotifications);

			sendEventsToSubscribers(userNotificationsResponse);
		}

		return mapper.mapEntitiesToCreateResponses(userNotifications);
	}

	@Override
	public List<UserNotificationResponse> getByLoggedInUserId() {
		String userId = AccountUtil.getLoggedInUserAccountInfo().getUserId();
		List<UserNotification> userNotifications = repository.findByReceiverIdAndRead(userId, Boolean.FALSE);
		return mapper.mapEntitiesToDTOs(userNotifications);
	}

	@Override
	public SseEmitter subscribe() {
		SseEmitter sseEmitter = new SseEmitter();
		synchronized (this.sseEmitters) {
			this.sseEmitters.add(sseEmitter);
			sseEmitter.onCompletion(() -> {
				synchronized (this.sseEmitters) {
					this.sseEmitters.remove(sseEmitter);
				}
			});
			sseEmitter.onTimeout(sseEmitter::complete);
		}
		return sseEmitter;
	}

	private void sendEventsToSubscribers(List<UserNotificationResponse> userNotificationsResponse) {
		synchronized (this.sseEmitters) {
			for (SseEmitter sseEmitter : this.sseEmitters) {
				try {
					sseEmitter.send(userNotificationsResponse, MediaType.APPLICATION_JSON);
					sseEmitter.complete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public UpdateUserNotificationResponse update(String id,
			UpdateUserNotificationRequest updateUserNotificationRequest) {
		Optional<UserNotification> optionalUserNotification= repository.findById(id);
		if(!optionalUserNotification.isPresent())
		{
			throw new DataNotFoundException(ErrorResponseConstants.USER_NOTIFICATION_NOT_FOUND_ID);
		}
		UserNotification userNotification=optionalUserNotification.get();
		userNotification.setRead(updateUserNotificationRequest.getRead());
		userNotification=repository.save(userNotification);
		
		return mapper.mapEntityToUpdateUserNotificationResponse(userNotification);
	}
}
