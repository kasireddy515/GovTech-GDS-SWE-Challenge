package com.govtech.assignment.service.impl;

import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.govtech.assignment.entity.Restaurant;
import com.govtech.assignment.entity.Session;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.mapper.RestaurantMapper;
import com.govtech.assignment.repository.RestaurantRepository;
import com.govtech.assignment.request.AddRestaurantToSessionRequest;
import com.govtech.assignment.response.AddRestaurantToSessionResponse;
import com.govtech.assignment.response.RestaurantResponse;
import com.govtech.assignment.service.RestaurantService;
import com.govtech.assignment.service.SessionService;
import com.govtech.assignment.service.UserService;
import com.govtech.assignment.util.AccountUtil;
import com.govtech.assignment.validator.SessionValidator;
import com.govtech.assignment.validator.UserValidator;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final RestaurantRepository repository;
	private final RestaurantMapper mapper;
	private final SessionService sessionService;
	private final SessionValidator validator;
	private final UserService userService;
	private final UserValidator userValidator;

	RestaurantServiceImpl(RestaurantRepository repository, RestaurantMapper mapper, SessionService sessionService,
			SessionValidator validator, UserService userService, UserValidator userValidator) {
		this.repository = repository;
		this.mapper = mapper;
		this.sessionService = sessionService;
		this.validator = validator;
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@Override
	public AddRestaurantToSessionResponse suggestRestaurantToSession(String sessionId,
			AddRestaurantToSessionRequest request) {

		Session session = validator.validateInvalidSessionId(sessionService.findById(sessionId));
		User user = userService.findById(AccountUtil.getLoggedInUserAccountInfo().getUserId());
		user = userValidator.validateInvalidUserId(user);

		Restaurant restaurant = mapper.mapRequestToEntiity(request, user);
		restaurant = repository.save(restaurant);

		sessionService.suggestRestaurantToSession(restaurant, session);

		return mapper.mapEntityToIDDTO(restaurant);
	}

	@Override
	public Set<RestaurantResponse> getSessionSuggestRestaurants(String sessionId) {
		Session session = validator.validateInvalidSessionId(sessionService.findById(sessionId));
		return mapper.modelsToDtos(session.getSubmittedRestaurants());
	}

	@Override
	public RestaurantResponse selectSessionSuggestRestaurant(String sessionId) {
		Session session = validator.validateInvalidSessionId(sessionService.findById(sessionId));
		RestaurantResponse restaurantResponse = null;
		Set<Restaurant> submittedRestaurants = session.getSubmittedRestaurants();
		if (submittedRestaurants != null && !submittedRestaurants.isEmpty()) {
			Restaurant randomRestaurant = submittedRestaurants.stream()
					.skip(new Random().nextInt(submittedRestaurants.size())).findFirst().orElse(null);

			if (randomRestaurant != null) {
				restaurantResponse = mapper.modelToDto(randomRestaurant);
			}
		}
		return restaurantResponse;
	}

}
