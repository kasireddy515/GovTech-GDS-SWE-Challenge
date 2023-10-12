package com.govtech.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = -1801957870998831764L;
	private String message;

}
