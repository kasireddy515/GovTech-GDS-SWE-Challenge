package com.govtech.assignment.mapper;

import java.util.Date;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.govtech.assignment.entity.Restaurant;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.request.AddRestaurantToSessionRequest;
import com.govtech.assignment.response.AddRestaurantToSessionResponse;
import com.govtech.assignment.response.RestaurantResponse;
import com.govtech.assignment.util.CommonUtil;

@Mapper
public interface RestaurantMapper {

	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
	UserMapper USER_MAPPER = UserMapper.INSTANCE;

	@Mapping(source = "request.title", target = "title")
	@Mapping(source = "request.location", target = "location")
	@Mapping(source = "user", target = "suggestedUser")
	Restaurant mapRequestToEntiity(AddRestaurantToSessionRequest request, User user);

	@AfterMapping
	default void populateNewRestaurantAuditInfo(AddRestaurantToSessionRequest request, User user,
			@MappingTarget Restaurant restaurant) {

		Date instant = CommonUtil.getCurrentGMTDate();

		restaurant.setCreatedOn(instant);
		restaurant.setUpdatedOn(instant);
		restaurant.setId(CommonUtil.generateId());

	}

	@Mapping(source = "id", target = "id")
	AddRestaurantToSessionResponse mapEntityToIDDTO(Restaurant restaurant);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "title", target = "title")
	@Mapping(source = "location", target = "location")
	@Mapping(source = "createdOn", target = "createdOn")
	@Mapping(source = "updatedOn", target = "updatedOn")
	RestaurantResponse modelToDto(Restaurant submittedRestaurant);

	Set<RestaurantResponse> modelsToDtos(Set<Restaurant> submittedRestaurants);

	@AfterMapping
	default void populateRestaurantInfo(Restaurant restaurant, @MappingTarget RestaurantResponse restaurantResponse) {

		if (restaurant.getSuggestedUser() != null) {
			restaurantResponse.setSuggestedUser(USER_MAPPER.modelToDto(restaurant.getSuggestedUser()));
		}

	}

}
