package com.govtech.assignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.govtech.assignment.constant.EndpointConstants;
import com.govtech.assignment.exception.ExceptionResponse;
import com.govtech.assignment.request.CreateUserNotificationRequest;
import com.govtech.assignment.request.UpdateUserNotificationRequest;
import com.govtech.assignment.response.AccountResponse;
import com.govtech.assignment.response.CreateUserNotificationResponse;
import com.govtech.assignment.response.SignInResponse;
import com.govtech.assignment.response.UpdateUserNotificationResponse;
import com.govtech.assignment.response.UserNotificationResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Notification", description = "Manage user notification APIs")
@RequestMapping(EndpointConstants.USER_NOTIFICATION_API_PATH)
public interface UserNotificationController {

	@Operation(summary = "Send notification to user(s)")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Return user notification information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid session identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@PostMapping(EndpointConstants.DETAILS_BY_SESSION_ID_API_PATH)
	public ResponseEntity<List<CreateUserNotificationResponse>> create(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.RESTAURANT_SESSION_ID_PATH_VARIABLE) String id,
			@Valid @RequestBody CreateUserNotificationRequest createUserNotificationRequest);

	@Operation(summary = "Retrieve notifications for the logged in user")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return user notifications information.", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than GET is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@GetMapping
	public ResponseEntity<List<UserNotificationResponse>> getByLoggedInUserId();
	
	@Operation(summary = "Subscribe to user notifications")
	@GetMapping(EndpointConstants.SUBSCRIBE_API_PATH)
	public ResponseEntity<SseEmitter> subscribe();

	
	@Operation(summary = "Send notification to user(s)")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Return user notification information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid session identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@PutMapping(EndpointConstants.DETAILS_BY_ID_API_PATH)
	public ResponseEntity<UpdateUserNotificationResponse> update(
			@Schema(title = "User notification id", description = "Identifier of the user noticiation", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String id,
			@Valid @RequestBody UpdateUserNotificationRequest createUserNotificationRequest);


}
