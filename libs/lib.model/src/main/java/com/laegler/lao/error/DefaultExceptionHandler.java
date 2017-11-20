package com.laegler.lao.error;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Order(value = LOWEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class DefaultExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneralException(final Exception exception) {
		String errorMessage = String.format("Unexpected Server Error: %s", exception.getMessage());
		LOG.error(errorMessage, exception);

		ErrorResponse error = new ErrorResponse(errorMessage);
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
	}

}
