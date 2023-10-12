package com.govtech.assignment.response;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class CreateSessionInvitesResponse implements Serializable {

	private static final long serialVersionUID = -906316750703816375L;

	private Set<String> sessionUserInviteIds;
}
