package com.govtech.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
@Schema(title = "Forgot password of the user account request", description = "Parameters to forgot password of the user account response")
public class ForgotPasswordRequest implements Serializable {

	private static final long serialVersionUID = 1318833735462766836L;
	
	@NotBlank(message = "User name is required.")
	@Schema(title = "User name", description = "User name", requiredMode = RequiredMode.REQUIRED)
	private String userName;

}
