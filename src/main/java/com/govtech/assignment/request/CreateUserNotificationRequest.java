package com.govtech.assignment.request;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateUserNotificationRequest implements Serializable{

	private static final long serialVersionUID = -855016562914922522L;
	
	@NotEmpty(message = "Message is required.")
	private String message;
	
	@NotNull(message = "User Ids are required.")
	@NotEmpty(message = "User Ids are required.")
	private Set<String> userIds;

}
