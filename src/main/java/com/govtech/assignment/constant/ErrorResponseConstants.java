package com.govtech.assignment.constant;

public class ErrorResponseConstants {

	private ErrorResponseConstants() {
	}

	public static final String USER_EXISTS_FIRST_LAST_NAME = "An User already exists with provided first and last name";
	public static final String ACCOUNT_EXISTS_FIRST_LAST_NAME = "An account already exists with provided user name";
	public static final String ACCOUNT_NOT_FOUND_USER_NAME = "Account does not exists with the provided username";
	public static final String INVALID_ACCOUNT_PASSWORD = "Invalid account password";
	public static final String ACCOUNT_NOT_FOUND_ID = "Account does not exists with the provided id";
	public static final String INVALID_ACCOUNT_NEW_PASSWORD = "Your new password cannot be the same as your old password";
	public static final String INVALID_ACCOUNT_OLD_PASSWORD = "Account does not exist with provided old account password";
	public static final String SESSION_EXISTS_TITLE = "A Session already exists with provided title";
	public static final String USER_NOT_FOUND_ID = "User does not exists with the provided id";
	public static final String USER_NOT_FOUND_ACCOUNT_ID = "User does not exists with the provided account id";
	public static final String SESSION_NOT_FOUND_ID = "Session does not exists with the provided id";
	public static final String SESSION_NOT_OWNER_ID = "You are not authroized to do this transaction as this session does not created by you";
	public static final String USER_NOTIFICATION_NOT_FOUND_ID = "User notification does not exists with the provided id";
}
