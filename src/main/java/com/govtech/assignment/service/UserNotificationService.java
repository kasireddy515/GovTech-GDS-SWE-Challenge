package com.govtech.assignment.service;

import java.util.List;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.govtech.assignment.request.CreateUserNotificationRequest;
import com.govtech.assignment.request.UpdateUserNotificationRequest;
import com.govtech.assignment.response.CreateUserNotificationResponse;
import com.govtech.assignment.response.UpdateUserNotificationResponse;
import com.govtech.assignment.response.UserNotificationResponse;

public interface UserNotificationService {

	List<CreateUserNotificationResponse> create(String sessionId, CreateUserNotificationRequest createUserNotificationRequest);

	List<UserNotificationResponse> getByLoggedInUserId();

	SseEmitter subscribe();

	UpdateUserNotificationResponse update(String id, UpdateUserNotificationRequest updateUserNotificationRequest);

}
