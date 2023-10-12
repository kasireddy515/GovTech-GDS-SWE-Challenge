package com.govtech.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
@Schema(title = "Reset password of the user account request", description = "Parameters to reset password of the user account response")
public class ResetPasswordRequest implements Serializable {

	private static final long serialVersionUID = 781292163701146826L;
	
	@NotBlank(message = "The password is required.")
	@Schema(title = "User account password", description = "User account new password", requiredMode = RequiredMode.REQUIRED)
	private String password;


}
