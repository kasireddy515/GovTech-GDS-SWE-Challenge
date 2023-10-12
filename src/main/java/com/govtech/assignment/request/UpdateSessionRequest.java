package com.govtech.assignment.request;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class UpdateSessionRequest implements Serializable {

	private static final long serialVersionUID = 1899348603010286767L;

	private String title;
	private String description;
	private Set<String> userIds;
	private Boolean active;

}
