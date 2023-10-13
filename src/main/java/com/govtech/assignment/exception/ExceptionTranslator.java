package com.govtech.assignment.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

		List<ExceptionResponse> errorResponse = prepareErrorsResponse(errors);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidArgumentException.class)
	public ResponseEntity<Object> handleInvalidArgumentExceptions(InvalidArgumentException exception,
			WebRequest webRequest) {
		List<ExceptionResponse> errorResponse = exception.getErrors();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataConflictException.class)
	public ResponseEntity<Object> handleDataConflictExceptions(DataConflictException exception, WebRequest webRequest) {
		ExceptionResponse response = prepareErrorResponse(exception.getMessage());
		List<ExceptionResponse> errorResponse = new ArrayList<>();
		errorResponse.add(response);
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<Object> handleUnAuthorizedExceptions(UnAuthorizedException exception, WebRequest webRequest) {
		ExceptionResponse response = prepareErrorResponse(exception.getMessage());
		List<ExceptionResponse> errorResponse = new ArrayList<>();
		errorResponse.add(response);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> handleDataNotFoundExceptions(DataNotFoundException exception, WebRequest webRequest) {
		ExceptionResponse response = prepareErrorResponse(exception.getMessage());
		List<ExceptionResponse> errorResponse = new ArrayList<>();
		errorResponse.add(response);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<Object> handleInvalidDataExceptions(InvalidDataException exception, WebRequest webRequest) {
		ExceptionResponse response = prepareErrorResponse(exception.getMessage());
		List<ExceptionResponse> errorResponse = new ArrayList<>();
		errorResponse.add(response);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	private List<ExceptionResponse> prepareErrorsResponse(List<String> errors) {
		List<ExceptionResponse> errorsResponse = new ArrayList<>();
		if (!CollectionUtils.isEmpty(errors)) {
			for (String error : errors) {
				ExceptionResponse errorResponse = prepareErrorResponse(error);
				errorsResponse.add(errorResponse);
			}
		}
		return errorsResponse;
	}

	private ExceptionResponse prepareErrorResponse(String error) {
		ExceptionResponse errorResponse = new ExceptionResponse();
		errorResponse.setMessage(error);
		errorResponse.setTime(LocalDateTime.now());
		return errorResponse;
	}

}
