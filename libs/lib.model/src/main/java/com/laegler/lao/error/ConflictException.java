package com.laegler.lao.error;

import static org.springframework.http.HttpStatus.CONFLICT;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = CONFLICT)
public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 4984452435921216278L;

	public ConflictException(final String message) {
		super(message);
	}

}
