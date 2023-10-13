package com.govtech.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class UnAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1879042211645847779L;
	private String message;
}

