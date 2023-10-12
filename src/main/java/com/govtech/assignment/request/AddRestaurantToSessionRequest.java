package com.govtech.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddRestaurantToSessionRequest implements Serializable {

	private static final long serialVersionUID = -8608148333969421935L;

	@NotBlank(message = "Title is required.")
	private String title;

	@NotBlank(message = "Location is required.")
	private String location;
}
