package com.impetus.casestudy.microservice.registeruser.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
		extends
			ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = {DataAccessException.class})
	protected ResponseEntity<Object> handleConflict(
			DataAccessException dataAccessException, WebRequest request) {
		String bodyOfResponse = dataAccessException.getCause().getMessage();
		logger.info("Error Occured.."
				+ dataAccessException.getStackTrace().toString());
		return handleExceptionInternal(dataAccessException, bodyOfResponse,
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
