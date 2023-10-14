package com.govtech.assignment.dto;

import java.io.Serializable;

import com.govtech.assignment.entity.Session;
import com.govtech.assignment.entity.User;

import lombok.Data;

@Data
public class CreateUserNotificationDTO implements Serializable{

	
	private static final long serialVersionUID = -8950503336070205094L;
	
	private User sender;
	private User receiver;
	private Session session;
	private String message;
}
