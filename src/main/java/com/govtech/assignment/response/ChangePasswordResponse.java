package com.govtech.assignment.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Change password of the user account response", description = "Parameters for the change password of the user account response")
public class ChangePasswordResponse implements Serializable {

	private static final long serialVersionUID = 7439980153957386303L;

	@Schema(title = "User account id", description = "Account identifier of the logged in user")
	private String id;

}
