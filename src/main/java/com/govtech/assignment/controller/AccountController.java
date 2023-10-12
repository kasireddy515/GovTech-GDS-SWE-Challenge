package com.govtech.assignment.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.govtech.assignment.constant.EndpointConstants;
import com.govtech.assignment.exception.ExceptionResponse;
import com.govtech.assignment.request.ChangePasswordRequest;
import com.govtech.assignment.request.ForgotPasswordRequest;
import com.govtech.assignment.request.ResetPasswordRequest;
import com.govtech.assignment.request.SignInRequest;
import com.govtech.assignment.response.AccountResponse;
import com.govtech.assignment.response.ChangePasswordResponse;
import com.govtech.assignment.response.ForgotPasswordResponse;
import com.govtech.assignment.response.ResetPasswordResponse;
import com.govtech.assignment.response.SignInResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Account", description = "Manage user account related APIs")
@RequestMapping(EndpointConstants.ACCOUNT_API_PATH)
public interface AccountController {

	@Operation(summary = "Sign into user account")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return logged in user information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid email supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid password supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "400", description = "Account is not verified yet.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "400", description = "Account is not pending status.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "400", description = "Account is not active.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@PostMapping(EndpointConstants.ACCOUNT_SIGN_IN_API_PATH)
	public ResponseEntity<SignInResponse> signInAccount(@Valid @RequestBody SignInRequest request);

	@Operation(summary = "Forgot password of an user account")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return account identifier if reset account email sent successfully.", content = @Content(schema = @Schema(implementation = ForgotPasswordResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid account identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@PostMapping(EndpointConstants.ACCOUNT_FORGOT_PASSWORD_API_PATH)
	public ResponseEntity<ForgotPasswordResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request);

	@Operation(summary = "reset password of an user account")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return account identifier if password reset is successfull.", content = @Content(schema = @Schema(implementation = ResetPasswordResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid account identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid new password supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than PUT is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@PutMapping(EndpointConstants.ACCOUNT_RESET_PASSWORD_API_PATH)
	public ResponseEntity<ResetPasswordResponse> resetPassword(
			@Schema(title = "User account id", description = "Account identifier of the user to change password", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String accountId,
			@Valid @RequestBody ResetPasswordRequest request);

	@Operation(summary = "Change password of an user account")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return account identifier if password changed is successfull.", content = @Content(schema = @Schema(implementation = ChangePasswordResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid account identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid account old password supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than PUT is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@PutMapping(EndpointConstants.ACCOUNT_CHANGE_PASSWORD_API_PATH)
	public ResponseEntity<ChangePasswordResponse> changePassword(
			@Schema(title = "User account id", description = "Account identifier of the user to change password", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String accountId,
			@Valid @RequestBody ChangePasswordRequest request);

	@Operation(summary = "Retrieve account details of a user by account identifier")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return user account information.", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid account identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than GET is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@GetMapping(EndpointConstants.DETAILS_BY_ID_API_PATH)
	public ResponseEntity<AccountResponse> getByAccountId(
			@Schema(title = "User account id", description = "Account identifier of the user", requiredMode = RequiredMode.REQUIRED)@PathVariable(EndpointConstants.ID_PATH_VARIABLE) String id);

}
