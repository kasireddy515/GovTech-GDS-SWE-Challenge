package com.govtech.assignment.response;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RestaurantResponse implements Serializable {

	private static final long serialVersionUID = -8467350695969992548L;
	private String id;
	private String title;
	private String location;
	private UserResponse suggestedUser;
	private Date createdOn;
	private Date updatedOn;
}
