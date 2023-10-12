package com.govtech.assignment.response;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class SessionResponse implements Serializable {

	private static final long serialVersionUID = 241846341444108704L;

	private String id;
	private String title;
	private String description;
	private UserResponse createdBy;
	private Set<UserResponse> usersInvited = new HashSet<>();
	private Set<RestaurantResponse> submittedRestaurants = new HashSet<>();
	private Boolean active;
	private Date createdOn;
	private Date updatedOn;

}
