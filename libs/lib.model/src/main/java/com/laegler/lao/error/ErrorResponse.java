package com.laegler.lao.error;

import lombok.Getter;
import javax.print.attribute.standard.Severity;

public class ErrorResponse {

	@Getter
	private final String message;

	@Getter
	private final Severity severity;

	public ErrorResponse(String message, Severity severity) {
		this.message = message;
		this.severity = severity;
	}

	public ErrorResponse(String message) {
		this.message = message;
		this.severity = Severity.ERROR;
	}

}
