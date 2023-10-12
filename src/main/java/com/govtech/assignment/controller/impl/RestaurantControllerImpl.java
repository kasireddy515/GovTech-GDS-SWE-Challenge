package com.govtech.assignment.controller.impl;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.govtech.assignment.controller.RestaurantController;
import com.govtech.assignment.request.AddRestaurantToSessionRequest;
import com.govtech.assignment.response.AddRestaurantToSessionResponse;
import com.govtech.assignment.response.RestaurantResponse;
import com.govtech.assignment.service.RestaurantService;

@RestController
public class RestaurantControllerImpl implements RestaurantController {

	private final RestaurantService service;

	RestaurantControllerImpl(RestaurantService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<AddRestaurantToSessionResponse> suggestRestaurantToSession(String id,
			AddRestaurantToSessionRequest request) {
		return new ResponseEntity<>(service.suggestRestaurantToSession(id,request), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Set<RestaurantResponse>> getSessionSuggestRestaurants(String id) {
		return new ResponseEntity<>(service.getSessionSuggestRestaurants(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RestaurantResponse> selectSessionSuggestRestaurant(String id) {
		return new ResponseEntity<>(service.selectSessionSuggestRestaurant(id), HttpStatus.OK);
	}

}
