package com.laegler.lao.error;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.Locale;

@Order(value = HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(final NotFoundException e) {
		Throwable exception = getNestedException(e);

		String errorMessage = exception.getMessage();
		LOG.error(errorMessage, exception);

		ErrorResponse error = new ErrorResponse(errorMessage);
		return ResponseEntity.status(getHttpStatus(exception)).body(error);
	}

	@ResponseBody
	@ResponseStatus(CONFLICT)
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<?> handleConflictException(final ConflictException e) {
		Throwable exception = getNestedException(e);

		String errorMessage = exception.getMessage();
		LOG.error(errorMessage, exception);

		ErrorResponse error = new ErrorResponse(errorMessage);
		return ResponseEntity.status(getHttpStatus(exception)).body(error);
	}

	@ResponseBody
	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
		Locale locale = LocaleContextHolder.getLocale();
		String code = e.getBindingResult().getFieldError().getDefaultMessage();
		String errorMessage = messageSource.getMessage(code, null, locale);
		LOG.error(errorMessage, e);
		ErrorResponse error = new ErrorResponse(errorMessage);
		return ResponseEntity.status(getHttpStatus(e)).body(error);
	}

	protected HttpStatus getHttpStatus(final Throwable exception) {
		ResponseStatus annotation = AnnotatedElementUtils.findMergedAnnotation(exception.getClass(), ResponseStatus.class);
		if (annotation != null) {
			return annotation.value();
		}
		return INTERNAL_SERVER_ERROR;
	}

	private Throwable getNestedException(final Throwable exception) {
		if (exception.getCause() != null) {
			return getNestedException(exception.getCause());
		} else {
			return exception;
		}
	}

}
