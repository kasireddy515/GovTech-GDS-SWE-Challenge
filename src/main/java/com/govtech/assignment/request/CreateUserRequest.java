package com.govtech.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
@Schema(title = "Create user and account for the user request", description = "Parameters to create user and account for the user request")
public class CreateUserRequest implements Serializable {

	private static final long serialVersionUID = 1013139901412733293L;

	@NotBlank(message = "First name is required.")
	@Schema(title = "User first name", description = "First name of the user", requiredMode = RequiredMode.REQUIRED)
	private String firstName;

	@NotBlank(message = "Last name is required.")
	@Schema(title = "User last name", description = "Last name of the user", requiredMode = RequiredMode.REQUIRED)
	private String lastName;

	@NotBlank(message = "User account name is required.")
	@Schema(title = "User account name", description = "Account name of the user", requiredMode = RequiredMode.REQUIRED)
	private String userName;

	@NotBlank(message = "Password is required.")
	@Schema(title = "User account password", description = "Account password of the user", requiredMode = RequiredMode.REQUIRED)
	private String password;

}
