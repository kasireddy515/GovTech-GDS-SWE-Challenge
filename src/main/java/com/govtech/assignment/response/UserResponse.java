package com.govtech.assignment.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 3450408287358449817L;

	private String id;
	private String firstName;
	private String lastName;
}
