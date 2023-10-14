package com.govtech.assignment.response;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserNotificationResponse implements Serializable {

	private static final long serialVersionUID = -869906972945548542L;

	private String id;
	private UserResponse sender;
	private UserResponse receiver;
	private SessionResponse session;
	private Boolean read;
	private String message;
	private Date createdOn;
	private Date updatedOn;

}
