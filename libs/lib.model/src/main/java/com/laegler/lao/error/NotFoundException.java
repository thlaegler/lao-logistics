package com.laegler.lao.error;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4984452435921216278L;

	public NotFoundException(final String message) {
		super(message);
	}

}
