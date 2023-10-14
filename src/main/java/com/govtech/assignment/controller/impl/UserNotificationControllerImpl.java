package com.govtech.assignment.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.govtech.assignment.controller.UserNotificationController;
import com.govtech.assignment.request.CreateUserNotificationRequest;
import com.govtech.assignment.request.UpdateUserNotificationRequest;
import com.govtech.assignment.response.CreateUserNotificationResponse;
import com.govtech.assignment.response.UpdateUserNotificationResponse;
import com.govtech.assignment.response.UserNotificationResponse;
import com.govtech.assignment.service.UserNotificationService;

@RestController
public class UserNotificationControllerImpl implements UserNotificationController {

	private final UserNotificationService service;

	UserNotificationControllerImpl(UserNotificationService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<List<CreateUserNotificationResponse>> create(String sessionId,
		CreateUserNotificationRequest createUserNotificationRequest) {
		
		return new ResponseEntity<>(service.create(sessionId,createUserNotificationRequest), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<UserNotificationResponse>> getByLoggedInUserId() {
		
		return new ResponseEntity<>(service.getByLoggedInUserId(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SseEmitter> subscribe() {
		return new ResponseEntity<>(service.subscribe(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UpdateUserNotificationResponse> update(String id,
			UpdateUserNotificationRequest updateUserNotificationRequest) {
		return new ResponseEntity<>(service.update(id,updateUserNotificationRequest), HttpStatus.OK);
	}
}
