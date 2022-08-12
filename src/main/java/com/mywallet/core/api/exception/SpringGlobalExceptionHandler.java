package com.mywallet.core.api.exception;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mywallet.core.api.dto.ApiErrorDTO;

@ControllerAdvice
public class SpringGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String RESPONSESTATUS_INTERNAL_MESSAGE = "Error executing operation: ";
	private static final String HTTPMESSAGENOTREADABLE_INTERNAL_MESSAGE = "Error processing request data: ";
	private static final String HTTPMESSAGENOTREADABLE_PUBLIC_MESSAGE = "There was an error processing the request data.";
	private static final String INTERNALSERVERERROR_INTERNAL_MESSAGE = "There was an internal error: ";
	private static final String INTERNALSERVERERROR_PUBLIC_MESSAGE = "There was an error executing your request, contact support!";

	private Logger logger = LoggerFactory.getLogger(SpringGlobalExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error(HTTPMESSAGENOTREADABLE_INTERNAL_MESSAGE+exception.getMessage() + (Objects.isNull(exception.getCause()) ? "" : " - Cause: "+exception.getCause().getMessage()));
        return new ResponseEntity<Object>(createApiErrorDTO(HttpStatus.FORBIDDEN, HTTPMESSAGENOTREADABLE_PUBLIC_MESSAGE, request), new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ResponseStatusException.class)
	protected ResponseEntity<Object> responseStatusExceptionHandler(ResponseStatusException exception, WebRequest request) {
		logger.error(RESPONSESTATUS_INTERNAL_MESSAGE + exception.getMessage() + (Objects.isNull(exception.getCause()) ? "" : " - Cause: "+exception.getCause().getMessage()));
		return new ResponseEntity<Object>(createApiErrorDTO(exception.getStatus(), exception.getReason(), request), new HttpHeaders(), exception.getStatus());
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> exceptionDefaultHandler(Exception exception, WebRequest request) {
		logger.error(INTERNALSERVERERROR_INTERNAL_MESSAGE + exception.getMessage() + (Objects.isNull(exception.getCause()) ? "" : " - Cause: "+exception.getCause().getMessage()));
		return new ResponseEntity<Object>(createApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, INTERNALSERVERERROR_PUBLIC_MESSAGE, request), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	private ApiErrorDTO createApiErrorDTO(HttpStatus status, String message, WebRequest request) {
		return ApiErrorDTO.valueOf(status.value(), status.getReasonPhrase(), message, ((ServletWebRequest) request).getRequest().getRequestURI());
	}
}
