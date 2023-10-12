package com.govtech.assignment.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Create user response", description = "Parameters for the create user response")
public class CreateUserResponse implements Serializable {

	private static final long serialVersionUID = -3771064712637745010L;

	@Schema(title = "User id", description = "User identifier of the logged in user")
	private String id;

	@Schema(title = "User account id", description = "Account identifier of the logged in user")
	private String accountId;

}
