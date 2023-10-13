package com.govtech.assignment.service.impl;

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

import com.govtech.assignment.entity.Restaurant;
import com.govtech.assignment.entity.Session;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.mapper.RestaurantMapper;
import com.govtech.assignment.repository.RestaurantRepository;
import com.govtech.assignment.request.AddRestaurantToSessionRequest;
import com.govtech.assignment.response.AddRestaurantToSessionResponse;
import com.govtech.assignment.response.RestaurantResponse;
import com.govtech.assignment.service.SessionService;
import com.govtech.assignment.service.UserService;
import com.govtech.assignment.validator.SessionValidator;
import com.govtech.assignment.validator.UserValidator;

@ExtendWith(SpringExtension.class)
public class RestaurantServiceImplTest {

	@InjectMocks
	private RestaurantServiceImpl service;

	@Mock
	private RestaurantRepository repository;

	@Mock
	private RestaurantMapper mapper;

	@Mock
	private SessionService sessionService;

	@Mock
	private SessionValidator validator;

	@Mock
	private UserService userService;

	@Mock
	private UserValidator userValidator;

	@Test
	void test_SuggestRestaurantToSession() {
		AddRestaurantToSessionResponse addRestaurantToSessionResponse = new AddRestaurantToSessionResponse();
		addRestaurantToSessionResponse.setId("1234567890");

		Session session = new Session();
		User user = new User();

		when(sessionService.findById(any())).thenReturn(session);

		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(validator.validateInvalidInviteeSessionId(any(),any())).thenReturn(session);
		when(userService.findById(any())).thenReturn(user);
		when(userValidator.validateInvalidUserId(any())).thenReturn(user);

		Restaurant restaurant = new Restaurant();
		when(mapper.mapRequestToEntiity(any(), any())).thenReturn(restaurant);
		when(repository.save(any())).thenReturn(restaurant);

		when(mapper.mapEntityToIDDTO(any())).thenReturn(addRestaurantToSessionResponse);

		AddRestaurantToSessionRequest addRestaurantToSessionRequest = new AddRestaurantToSessionRequest();
		AddRestaurantToSessionResponse actualAddRestaurantToSessionResponse = service
				.suggestRestaurantToSession("1234567890", addRestaurantToSessionRequest);
		Assertions.assertEquals(addRestaurantToSessionResponse.getId(), actualAddRestaurantToSessionResponse.getId());
	}

	@Test
	void test_GetSessionSuggestRestaurants() {
		Set<RestaurantResponse> restaurantsResponse = new HashSet<RestaurantResponse>();
		RestaurantResponse restaurantResponse = new RestaurantResponse();
		restaurantResponse.setId("1234567890");
		restaurantsResponse.add(restaurantResponse);
		Session session = new Session();
		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(mapper.modelsToDtos(any())).thenReturn(restaurantsResponse);
		Set<RestaurantResponse> actualRestaurantsResponse = service.getSessionSuggestRestaurants("1234567890");
		Assertions.assertEquals(restaurantsResponse.size(), actualRestaurantsResponse.size());
	}

	@Test
	void test_SelectSessionSuggestRestaurant() {
		RestaurantResponse restaurantResponse = new RestaurantResponse();
		restaurantResponse.setId("1234567890");
		Session session = new Session();
		Set<Restaurant> submittedRestaurants = new HashSet<>();
		Restaurant restaurant= new Restaurant();
		submittedRestaurants.add(restaurant);
		session.setSubmittedRestaurants(submittedRestaurants);
		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(mapper.modelToDto(any())).thenReturn(restaurantResponse);
		RestaurantResponse actualRestaurantResponse = service.selectSessionSuggestRestaurant("1234567890");
		Assertions.assertEquals(actualRestaurantResponse.getId(), restaurantResponse.getId());
	}
	
	@Test
	void test_SelectSessionSuggestRestaurant_None() {
		RestaurantResponse restaurantResponse = new RestaurantResponse();
		restaurantResponse.setId("1234567890");
		Session session = new Session();
		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(mapper.modelToDto(any())).thenReturn(restaurantResponse);
		RestaurantResponse actualRestaurantResponse = service.selectSessionSuggestRestaurant("1234567890");
		Assertions.assertNull(actualRestaurantResponse);
	}
	
	@Test
	void test_SelectSessionSuggestRestaurant_None_Empty() {
		RestaurantResponse restaurantResponse = new RestaurantResponse();
		restaurantResponse.setId("1234567890");
		Session session = new Session();
		Set<Restaurant> submittedRestaurants = new HashSet<>();
		session.setSubmittedRestaurants(submittedRestaurants);
		when(validator.validateInvalidSessionId(any())).thenReturn(session);
		when(mapper.modelToDto(any())).thenReturn(restaurantResponse);
		RestaurantResponse actualRestaurantResponse = service.selectSessionSuggestRestaurant("1234567890");
		Assertions.assertNull(actualRestaurantResponse);
	}
}
