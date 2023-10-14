package com.govtech.assignment.constant;

public class EndpointConstants {

	private EndpointConstants() {
	}

	public static final String USER_API_PATH = "/api/v1/user";
	public static final String USER_NOTIFICATION_API_PATH = "/api/v1/user-notification";
	public static final String ACCOUNT_API_PATH = "/api/v1/account";
	public static final String RESTAURANT_API_PATH = "/api/v1/restaurant";
	public static final String SESSION_API_PATH = "/api/v1/session";
	public static final String ACCOUNT_SIGN_IN_API_PATH = "/sign-in";
	public static final String ACCOUNT_FORGOT_PASSWORD_API_PATH = "/forgot-password";
	public static final String ACCOUNT_RESET_PASSWORD_API_PATH = "/reset-password/{id}";
	public static final String ID_PATH_VARIABLE = "id";
	public static final String ACCOUNT_CHANGE_PASSWORD_API_PATH = "/change-password/{id}";
	public static final String DETAILS_BY_ID_API_PATH = "/{id}";
	public static final String USER_ACCOUNT_ID_PATH_VARIABLE = "accountId";
	public static final String USER_DETAILS_BY_ACCOUNT_ID_API_PATH = "/account/{accountId}";
	public static final String FIND_API_PATH = "/{pageOffset}/{pageLimit}/{searchText}/{sortBy}/{sortOrder}";
	public static final String PAGE_OFFSET_PATH_VARIABLE = "pageOffset";
	public static final String PAGE_LIMIT_PATH_VARIABLE = "pageLimit";
	public static final String SEARCH_TEXT_PATH_VARIABLE = "searchText";
	public static final String SORT_BY_PATH_VARIABLE = "sortBy";
	public static final String SORT_ORDER_PATH_VARIABLE = "sortOrder";
	public static final String INVITE_USERS_API_PATH = "/invite-users/{id}";
	
	public static final String DETAILS_BY_SESSION_ID_API_PATH = "/{sessionId}";
	public static final String RESTAURANT_SESSION_ID_PATH_VARIABLE = "sessionId";
	public static final String SELECT_DETAILS_BY_SESSION_ID_API_PATH = "/select/{sessionId}";
	public static final String SUBSCRIBE_API_PATH = "/subscribe";
}
