package com.govtech.assignment.request;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateSessionInviteRequest implements Serializable {

	private static final long serialVersionUID = 1899348603010286767L;

	@NotNull(message = "User Ids are required.")
	@NotEmpty(message = "User Ids are required.")
	private Set<String> userIds;
}
