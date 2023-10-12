package com.govtech.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
@Schema(title = "User account sign in request", description = "Parameters required for user to sign into the application", requiredMode = RequiredMode.REQUIRED)
public class SignInRequest implements Serializable {

	private static final long serialVersionUID = 8721112895310860662L;

	@NotBlank(message = "User name is required.")
	@Schema(title = "User name", description = "User name", requiredMode = RequiredMode.REQUIRED)
	private String userName;

	@NotBlank(message = "Password is required.")
	@Schema(title = "Password", description = "User account password", requiredMode = RequiredMode.REQUIRED)
	private String password;

}
