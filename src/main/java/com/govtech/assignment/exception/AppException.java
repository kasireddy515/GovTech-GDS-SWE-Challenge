package com.govtech.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class AppException extends RuntimeException {

	private static final long serialVersionUID = -4652239978031615517L;
	private String message;
}
