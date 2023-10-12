package com.govtech.assignment.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(title = "Exception response", description = "Parameters for the exception response")
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = -1969327703093661498L;

	@Schema(title = "Error message", description = "Exception message")
	private String message;

	@Schema(title = "Exception occured time", description = "Time at which the exceptoin has occured")
	private LocalDateTime time;
}
