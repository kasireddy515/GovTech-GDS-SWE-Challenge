package com.govtech.assignment.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "User account response", description = "Parameters for the user account response")
public class AccountResponse implements Serializable {

	private static final long serialVersionUID = 2010994395799867747L;

	@Schema(title = "User account identifier", description = "Account identifier of the user")
	private String id;
	
	@Schema(title = "User account name", description = "Account name of the user")
	private String userName;
}
