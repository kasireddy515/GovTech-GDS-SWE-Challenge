package com.govtech.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
@Schema(title = "Create account for the user request", description = "Parameters to create account for the user request")
public class CreateAccountRequest implements Serializable {

	private static final long serialVersionUID = -2608570946372610933L;

	@NotBlank(message = "User account name is required.")
	@Schema(title = "User account name", description = "Account name of the user", requiredMode = RequiredMode.REQUIRED)
	private String userName;

	@NotBlank(message = "Password is required.")
	@Schema(title = "User account password", description = "Account password of the user", requiredMode = RequiredMode.REQUIRED)
	private String password;
}
