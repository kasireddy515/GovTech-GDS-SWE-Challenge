package com.govtech.assignment.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.govtech.assignment.constant.EndpointConstants;
import com.govtech.assignment.exception.ExceptionResponse;
import com.govtech.assignment.request.AddRestaurantToSessionRequest;
import com.govtech.assignment.response.AddRestaurantToSessionResponse;
import com.govtech.assignment.response.RestaurantResponse;
import com.govtech.assignment.response.SignInResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurant", description = "Manage restaurant related APIs")
@RequestMapping(EndpointConstants.RESTAURANT_API_PATH)
public interface RestaurantController {

	@Operation(summary = "Suggest restaurant to a session")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return restaurant identification information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid session identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@PostMapping(EndpointConstants.DETAILS_BY_SESSION_ID_API_PATH)
	public ResponseEntity<AddRestaurantToSessionResponse> suggestRestaurantToSession(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.RESTAURANT_SESSION_ID_PATH_VARIABLE) String id,
			@Valid @RequestBody AddRestaurantToSessionRequest request);

	
	@Operation(summary = "Retrieve suggested restaurants of a session")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return restaurant identification information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid session identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@GetMapping(EndpointConstants.DETAILS_BY_SESSION_ID_API_PATH)
	public ResponseEntity<Set<RestaurantResponse>> getSessionSuggestRestaurants(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.RESTAURANT_SESSION_ID_PATH_VARIABLE) String id);
	
	
	@Operation(summary = "Select a random restaurant from suggested restaurants of a session")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return restaurant identification information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid session identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@GetMapping(EndpointConstants.SELECT_DETAILS_BY_SESSION_ID_API_PATH)
	public ResponseEntity<RestaurantResponse> selectSessionSuggestRestaurant(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.RESTAURANT_SESSION_ID_PATH_VARIABLE) String id);

}
