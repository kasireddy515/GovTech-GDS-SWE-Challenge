package com.govtech.assignment.service;

import java.util.Set;

import com.govtech.assignment.request.AddRestaurantToSessionRequest;
import com.govtech.assignment.response.AddRestaurantToSessionResponse;
import com.govtech.assignment.response.RestaurantResponse;

public interface RestaurantService {

	AddRestaurantToSessionResponse suggestRestaurantToSession(String sessionId, AddRestaurantToSessionRequest request);

	Set<RestaurantResponse> getSessionSuggestRestaurants(String sessionId);

	RestaurantResponse selectSessionSuggestRestaurant(String id);

}
