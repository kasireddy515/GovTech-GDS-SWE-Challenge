package com.govtech.assignment.mapper;

import java.util.Date;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.govtech.assignment.dto.CreateUserNotificationDTO;
import com.govtech.assignment.entity.Session;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.entity.UserNotification;
import com.govtech.assignment.request.CreateUserNotificationRequest;
import com.govtech.assignment.response.CreateUserNotificationResponse;
import com.govtech.assignment.response.UpdateUserNotificationResponse;
import com.govtech.assignment.response.UserNotificationResponse;
import com.govtech.assignment.util.CommonUtil;

@Mapper
public interface UserNotificationMapper {

	UserNotificationMapper INSTANCE = Mappers.getMapper(UserNotificationMapper.class);
	UserMapper USER_MAPPER = UserMapper.INSTANCE;
	SessionMapper SESSION_MAPPER = SessionMapper.INSTANCE;

	@Mapping(source = "senderUser", target = "sender")
	@Mapping(source = "receiverUser", target = "receiver")
	@Mapping(source = "session", target = "session")
	@Mapping(source = "request.message", target = "message")
	CreateUserNotificationDTO mapCreateUserNotificationRequest(CreateUserNotificationRequest request, User senderUser,
			User receiverUser, Session session);

	@Mapping(source = "sender", target = "sender")
	@Mapping(source = "receiver", target = "receiver")
	@Mapping(source = "session", target = "session")
	@Mapping(source = "message", target = "message")
	UserNotification mapDTOToEntity(CreateUserNotificationDTO dto);

	List<UserNotification> mapDTOsToEntities(List<CreateUserNotificationDTO> createUserNotificationDTOs);

	@AfterMapping
	default void populateNewUserAuditInfo(CreateUserNotificationDTO request,
			@MappingTarget UserNotification userNotification) {

		Date instant = CommonUtil.getCurrentGMTDate();

		userNotification.setCreatedOn(instant);
		userNotification.setUpdatedOn(instant);
		userNotification.setId(CommonUtil.generateId());
		userNotification.setRead(true);

	}

	@Mapping(source = "id", target = "id")
	CreateUserNotificationResponse mapEntityToCreateResponse(UserNotification userNotification);

	List<CreateUserNotificationResponse> mapEntitiesToCreateResponses(List<UserNotification> userNotifications);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "read", target = "read")
	@Mapping(source = "message", target = "message")
	@Mapping(source = "createdOn", target = "createdOn")
	@Mapping(source = "updatedOn", target = "updatedOn")
	UserNotificationResponse mapEntityToDTO(UserNotification userNotification);

	List<UserNotificationResponse> mapEntitiesToDTOs(List<UserNotification> userNotifications);

	@AfterMapping
	default void populateResponseInfo(UserNotification userNotification,
			@MappingTarget UserNotificationResponse userNotificationResponse) {

		userNotificationResponse.setReceiver(USER_MAPPER.modelToDto(userNotification.getReceiver()));
		userNotificationResponse.setSender(USER_MAPPER.modelToDto(userNotification.getSender()));
		userNotificationResponse.setSession(SESSION_MAPPER.mapEntityToResponse(userNotification.getSession()));
	}

	@Mapping(source = "id", target = "id")
	UpdateUserNotificationResponse mapEntityToUpdateUserNotificationResponse(UserNotification userNotification);
}
