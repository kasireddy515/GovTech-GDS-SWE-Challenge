package com.govtech.assignment.response;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class UsersResponse implements Serializable {

	private static final long serialVersionUID = -3353656569048065916L;

	private Long total = 0L;
	private Set<UserResponse> users;
}
