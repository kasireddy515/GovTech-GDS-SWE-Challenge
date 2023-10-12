package com.govtech.assignment.request;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateSessionRequest implements Serializable {

	private static final long serialVersionUID = 1899348603010286767L;

	@NotBlank(message = "Title is required.")
	private String title;

	private String description;
	private Set<String> userIds;
}
