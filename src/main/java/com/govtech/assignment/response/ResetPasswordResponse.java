package com.govtech.assignment.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Reset password of the user account response", description = "Parameters for the reset password of the user account response")
public class ResetPasswordResponse implements Serializable {

	private static final long serialVersionUID = -5777160834766775222L;
	
	@Schema(title = "User account id", description = "Account identifier of the logged in user")
	private String id;

}
