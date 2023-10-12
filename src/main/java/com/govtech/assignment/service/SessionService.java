package com.govtech.assignment.service;

import java.util.Set;

import com.govtech.assignment.entity.Restaurant;
import com.govtech.assignment.entity.Session;
import com.govtech.assignment.request.CreateSessionInviteRequest;
import com.govtech.assignment.request.CreateSessionRequest;
import com.govtech.assignment.request.UpdateSessionRequest;
import com.govtech.assignment.response.CreateSessionInvitesResponse;
import com.govtech.assignment.response.CreateSessionResponse;
import com.govtech.assignment.response.SessionResponse;
import com.govtech.assignment.response.SessionsResponse;
import com.govtech.assignment.response.UpdateSessionResponse;
import com.govtech.assignment.response.UserResponse;

public interface SessionService {

	CreateSessionResponse create(CreateSessionRequest request);

	SessionsResponse find(Integer pageOffset, Integer pageLimit, String searchText, String sortBy, String sortOrder);

	SessionResponse getById(String sessionId);

	CreateSessionInvitesResponse invite(String sessionId, CreateSessionInviteRequest request);

	UpdateSessionResponse update(String sessionId, UpdateSessionRequest request);

	Boolean delete(String id);

	Set<UserResponse> getInvitees(String id);
	
	Session findById(String id);

	void suggestRestaurantToSession(Restaurant restaurant, Session session);

}
