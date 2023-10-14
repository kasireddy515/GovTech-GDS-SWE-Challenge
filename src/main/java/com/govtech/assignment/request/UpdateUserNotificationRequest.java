package com.govtech.assignment.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdateUserNotificationRequest implements Serializable{

	private static final long serialVersionUID = -855016562914922522L;
	private Boolean read;

}
