package com.govtech.assignment.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class InvalidArgumentException extends RuntimeException {

	private static final long serialVersionUID = 8425042177786008564L;
	
	List<ExceptionResponse> errors;
}
