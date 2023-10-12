package com.govtech.assignment.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "User account sign in response", description = "Parameters for the user account sign in response")
public class SignInResponse implements Serializable {

	private static final long serialVersionUID = -5313104321118393188L;

	@Schema(title = "User account id", description = "Account identifier of the logged in user")
	private String id;

	@Schema(title = "User account session token", description = "Session access token of the logged in user")
	private String accessToken;

}
