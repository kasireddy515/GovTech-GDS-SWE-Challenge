package com.govtech.assignment.response;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SessionMetaDataResponse implements Serializable {

	private static final long serialVersionUID = 3627916496057873855L;

	private String id;
	private String title;
	private String description;
	private UserResponse createdByUser;
	private Long noOfUsersInvited;
	private Long noOfSubmittedRestaurants;
	private Boolean active;
	private String selectedRestaurantName;
	private Date createdOn;
	private Date updatedOn;
	
}
