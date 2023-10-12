package com.govtech.assignment.mapper;

import java.util.Date;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.govtech.assignment.entity.Session;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.request.CreateSessionRequest;
import com.govtech.assignment.response.CreateSessionResponse;
import com.govtech.assignment.response.SessionMetaDataResponse;
import com.govtech.assignment.response.SessionResponse;
import com.govtech.assignment.response.UpdateSessionResponse;
import com.govtech.assignment.util.CommonUtil;

@Mapper
public interface SessionMapper {

	SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

	UserMapper USER_MAPPER = UserMapper.INSTANCE;

	RestaurantMapper RESTAURANT_MAPPER = RestaurantMapper.INSTANCE;

	@Mapping(source = "id", target = "id")
	CreateSessionResponse mapEntityToCreateSessionResponse(Session session);

	@Mapping(source = "request.title", target = "title")
	@Mapping(source = "request.description", target = "description")
	@Mapping(source = "user", target = "createdBy")
	Session mapCreateSessionRequestToEntity(CreateSessionRequest request, User user);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "title", target = "title")
	@Mapping(source = "description", target = "description")
	@Mapping(source = "active", target = "active")
	@Mapping(source = "createdOn", target = "createdOn")
	@Mapping(source = "updatedOn", target = "updatedOn")
	SessionMetaDataResponse modelToDto(Session session);

	List<SessionMetaDataResponse> modelsToDtos(List<Session> sessions);

	@AfterMapping
	default void populateNewSessionAuditInfo(CreateSessionRequest request, User user, @MappingTarget Session session) {

		Date instant = CommonUtil.getCurrentGMTDate();

		session.setCreatedOn(instant);
		session.setUpdatedOn(instant);
		session.setId(CommonUtil.generateId());
		session.setActive(Boolean.TRUE);

	}

	@AfterMapping
	default void populateSessionMetaDataInfo(Session session,
			@MappingTarget SessionMetaDataResponse sessionMetaDataResponse) {

		sessionMetaDataResponse.setCreatedByUser(USER_MAPPER.modelToDto(session.getCreatedBy()));
		sessionMetaDataResponse.setNoOfSubmittedRestaurants(
				session.getSubmittedRestaurants() != null ? session.getSubmittedRestaurants().size() : 0L);
		sessionMetaDataResponse
				.setNoOfUsersInvited(session.getUsersInvited() != null ? session.getUsersInvited().size() : 0L);
		sessionMetaDataResponse.setSelectedRestaurantName(
				session.getSelectedRestaurant() != null ? session.getSelectedRestaurant().getTitle() : "");

	}

	@Mapping(source = "id", target = "id")
	@Mapping(source = "title", target = "title")
	@Mapping(source = "description", target = "description")
	@Mapping(source = "active", target = "active")
	@Mapping(source = "createdOn", target = "createdOn")
	@Mapping(source = "updatedOn", target = "updatedOn")
	SessionResponse mapEntityToResponse(Session session);

	@AfterMapping
	default void populateSessionInfo(Session session, @MappingTarget SessionResponse sessionResponse) {

		if (session.getCreatedBy() != null) {
			sessionResponse.setCreatedBy(USER_MAPPER.modelToDto(session.getCreatedBy()));
		}

		if (session.getUsersInvited() != null && !session.getUsersInvited().isEmpty()) {
			sessionResponse.setUsersInvited(USER_MAPPER.modelsToDtos(session.getUsersInvited()));
		}

		if (session.getSubmittedRestaurants() != null && !session.getSubmittedRestaurants().isEmpty()) {
			sessionResponse.setSubmittedRestaurants(RESTAURANT_MAPPER.modelsToDtos(session.getSubmittedRestaurants()));
		}

		if (session.getSelectedRestaurant() != null) {
			sessionResponse.setSelectedRestaurant(RESTAURANT_MAPPER.modelToDto(session.getSelectedRestaurant()));
		}
	}

	@Mapping(source = "id", target = "id")
	UpdateSessionResponse mapEntityToUpdateSessionResponse(Session session);

}
