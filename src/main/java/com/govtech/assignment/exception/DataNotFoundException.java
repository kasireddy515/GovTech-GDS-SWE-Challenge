package com.govtech.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5724410417338807836L;

	private String message;
}
