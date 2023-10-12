package com.govtech.assignment.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Forgot password of the user account response", description = "Parameters for the forgot password of the user account response")
public class ForgotPasswordResponse implements Serializable {

	private static final long serialVersionUID = -6245799863395250549L;
	
	@Schema(title = "User account id", description = "Account identifier of the logged in user")
	private String id;

}
