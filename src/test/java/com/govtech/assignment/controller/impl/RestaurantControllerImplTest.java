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

import com.govtech.assignment.request.AddRestaurantToSessionRequest;
import com.govtech.assignment.response.AddRestaurantToSessionResponse;
import com.govtech.assignment.response.RestaurantResponse;
import com.govtech.assignment.service.RestaurantService;

@ExtendWith(SpringExtension.class)
public class RestaurantControllerImplTest {

	@InjectMocks
	private RestaurantControllerImpl controller;

	@Mock
	private RestaurantService service;

	@Test
	void test_SuggestRestaurantToSession() {
		AddRestaurantToSessionResponse addRestaurantToSessionResponse = new AddRestaurantToSessionResponse();
		addRestaurantToSessionResponse.setId("1234567890");
		when(service.suggestRestaurantToSession(any(), any())).thenReturn(addRestaurantToSessionResponse);
		AddRestaurantToSessionRequest addRestaurantToSessionRequest = new AddRestaurantToSessionRequest();
		AddRestaurantToSessionResponse actualAddRestaurantToSessionResponse = controller
				.suggestRestaurantToSession("1234567890", addRestaurantToSessionRequest).getBody();
		Assertions.assertEquals(addRestaurantToSessionResponse.getId(), actualAddRestaurantToSessionResponse.getId());
	}

	@Test
	void test_GetSessionSuggestRestaurants() {
		Set<RestaurantResponse> restaurantsResponse = new HashSet<RestaurantResponse>();
		RestaurantResponse restaurantResponse = new RestaurantResponse();
		restaurantResponse.setId("1234567890");
		restaurantsResponse.add(restaurantResponse);
		when(service.getSessionSuggestRestaurants(any())).thenReturn(restaurantsResponse);
		Set<RestaurantResponse> actualRestaurantsResponse = controller.getSessionSuggestRestaurants("1234567890")
				.getBody();
		Assertions.assertEquals(restaurantsResponse.size(), actualRestaurantsResponse.size());
	}

	@Test
	void test_SelectSessionSuggestRestaurant() {
		RestaurantResponse restaurantResponse = new RestaurantResponse();
		restaurantResponse.setId("1234567890");
		when(service.selectSessionSuggestRestaurant(any())).thenReturn(restaurantResponse);
		RestaurantResponse actualRestaurantResponse = controller.selectSessionSuggestRestaurant("1234567890").getBody();
		Assertions.assertEquals(actualRestaurantResponse.getId(), restaurantResponse.getId());
	}
}
