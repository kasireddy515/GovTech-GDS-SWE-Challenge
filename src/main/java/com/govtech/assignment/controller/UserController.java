package com.govtech.assignment.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.govtech.assignment.constant.EndpointConstants;
import com.govtech.assignment.exception.ExceptionResponse;
import com.govtech.assignment.request.CreateUserRequest;
import com.govtech.assignment.response.AccountResponse;
import com.govtech.assignment.response.CreateUserResponse;
import com.govtech.assignment.response.SignInResponse;
import com.govtech.assignment.response.UserResponse;
import com.govtech.assignment.response.UsersResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "Manage user related APIs")
@RequestMapping(EndpointConstants.USER_API_PATH)
public interface UserController {

	@Operation(summary = "Create user and account for the user")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Return logged in user information.", content = @Content(schema = @Schema(implementation = SignInResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "409", description = "User already exists.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than POST is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@PostMapping
	public ResponseEntity<CreateUserResponse> create(@Valid @RequestBody CreateUserRequest request);

	@Operation(summary = "Retrieve user details of a user by account identifier")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return user information.", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid account identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than GET is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@GetMapping(EndpointConstants.USER_DETAILS_BY_ACCOUNT_ID_API_PATH)
	public ResponseEntity<UserResponse> getByAccountId(
			@Schema(title = "User account id", description = "Account identifier of the user", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.USER_ACCOUNT_ID_PATH_VARIABLE) String id);

	@Operation(summary = "Retrieve user details of a user by identifier")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return user information.", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "404", description = "Invalid user identifier supplied.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than GET is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	
	@GetMapping(EndpointConstants.DETAILS_BY_ID_API_PATH)
	public ResponseEntity<UserResponse> getById(
			@Schema(title = "User id", description = "User identifier of the user", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.ID_PATH_VARIABLE) String id);

	
	@Operation(summary = "Retrieve list of users details of a user by pagination and search text")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Return users information.", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "404", description = "No users found for the given criteria", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "401", description = "Not authorized. Application/User is not authorized to call this service.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "405", description = "Method other than GET is not supported.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "406", description = "Request not acceptable according to the Accept-* header.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error. Correct request but a problem occurred on the server. Dependent service is not available.", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))) })

	@GetMapping(EndpointConstants.FIND_API_PATH)
	public ResponseEntity<UsersResponse> find(
			@Schema(title = "Offset", description = "Page offset", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.PAGE_OFFSET_PATH_VARIABLE) Integer pageOffset,
			@Schema(title = "Limit", description = "Page limit", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.PAGE_LIMIT_PATH_VARIABLE) Integer pageLimit,
			@Schema(title = "Search text", description = "Search text", requiredMode = RequiredMode.NOT_REQUIRED) @PathVariable(EndpointConstants.SEARCH_TEXT_PATH_VARIABLE) String searchText,
			@Schema(title = "Sort by field", description = "Sort by fields", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.SORT_BY_PATH_VARIABLE) String sortBy,
			@Schema(title = "Sort order", description = "Sort order", requiredMode = RequiredMode.REQUIRED) @PathVariable(EndpointConstants.SORT_ORDER_PATH_VARIABLE) String sortOrder);
}
