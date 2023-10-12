package com.govtech.assignment.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.govtech.assignment.constant.EndpointConstants;
import com.govtech.assignment.exception.ExceptionResponse;
import com.govtech.assignment.request.CreateSessionInviteRequest;
import com.govtech.assignment.request.CreateSessionRequest;
import com.govtech.assignment.request.UpdateSessionRequest;
import com.govtech.assignment.response.AccountResponse;
import com.govtech.assignment.response.CreateSessionInvitesResponse;
import com.govtech.assignment.response.CreateSessionResponse;
import com.govtech.assignment.response.SessionResponse;
import com.govtech.assignment.response.SessionsResponse;
import com.govtech.assignment.response.SignInResponse;
import com.govtech.assignment.response.UpdateSessionResponse;
import com.govtech.assignment.response.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Session", description = "Manage session related APIs")
@RequestMapping(EndpointConstants.SESSION_API_PATH)
public interface SessionController {

	@Operation(summary = "Create session")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Return session identifier information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "409", description = "Session already exists.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })
	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@PostMapping
	public ResponseEntity<CreateSessionResponse> create(@Valid @RequestBody CreateSessionRequest request);

	@Operation(summary = "Retrieve sessions by pagination and search text")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return sessions information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@GetMapping(EndpointConstants.FIND_API_PATH)
	public ResponseEntity<SessionsResponse> find(
			@Schema(title = "Offset", description = "Page offset", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.PAGE_OFFSET_PATH_VARIABLE) Integer pageOffset,
			@Schema(title = "Limit", description = "Page limit", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.PAGE_LIMIT_PATH_VARIABLE) Integer pageLimit,
			@Schema(title = "Search text", description = "Search text", requiredMode = RequiredMode.NOT_REQUIRED) @PathVariable(EndpointConstants.SEARCH_TEXT_PATH_VARIABLE) String searchText,
			@Schema(title = "Sort by field", description = "Sort by fields", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.SORT_BY_PATH_VARIABLE) String sortBy,
			@Schema(title = "Sort order", description = "Sort order", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.SORT_ORDER_PATH_VARIABLE) String sortOrder);

	@Operation(summary = "Retrieve session details of a session by identifier")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return session information.", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid session identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than GET is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@GetMapping(EndpointConstants.DETAILS_BY_ID_API_PATH)
	public ResponseEntity<SessionResponse> getById(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String id);

	@Operation(summary = "Send session invite to users")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Return session invites information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@PostMapping(EndpointConstants.INVITE_USERS_API_PATH)
	public ResponseEntity<CreateSessionInvitesResponse> invite(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String id,
			@Valid @RequestBody CreateSessionInviteRequest request);

	@Operation(summary = "Retrieve session invitees")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Retrieve session invitees session invites information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@GetMapping(EndpointConstants.INVITE_USERS_API_PATH)
	public ResponseEntity<Set<UserResponse>> getInvitees(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String id);

	@Operation(summary = "Update session details of a session by identifier")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return session identifier.", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid session identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than GET is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@PutMapping(EndpointConstants.DETAILS_BY_ID_API_PATH)
	public ResponseEntity<UpdateSessionResponse> update(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String id,
			@Valid @RequestBody UpdateSessionRequest request);

	@Operation(summary = "Delete session by identifier")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return session identifier.", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid session identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than GET is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@Parameter(description = "JWT Token : Bearer token", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vyb25lIiwiaWF0IjoxNjk3MDc5MDkzLCJleHAiOjE2OTcxNjU0OTN9.RB70XduaPoPRDkF5HacJQyVr9GOFJZHMPM3gNJEWOY9nxMyNbie8iJ0N3ZCiN6Rnu-Wl-RsfyYgVh7xc3v_LXA", required = true, schema = @Schema(type = "string"), name = "Authorization", in = ParameterIn.HEADER)
	@DeleteMapping(EndpointConstants.DETAILS_BY_ID_API_PATH)
	public ResponseEntity<Boolean> delete(
			@Schema(title = "Session id", description = "Identifier of the session", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String id);

}
