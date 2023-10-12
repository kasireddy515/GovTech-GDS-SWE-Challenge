package com.govtech.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
@Schema(title = "Change user account password request", description = "Parameters required for user to change account password", requiredMode = RequiredMode.REQUIRED)
public class ChangePasswordRequest implements Serializable {

	private static final long serialVersionUID = -2067778812782481017L;

	@NotBlank(message = "The old password is required.")
	@Schema(title = "User account old password", description = "User account old password", requiredMode = RequiredMode.REQUIRED)
	private String oldPassword;

	@NotBlank(message = "The password is required.")
	@Schema(title = "User account new password", description = "User account new password", requiredMode = RequiredMode.REQUIRED)
	private String password;

}
