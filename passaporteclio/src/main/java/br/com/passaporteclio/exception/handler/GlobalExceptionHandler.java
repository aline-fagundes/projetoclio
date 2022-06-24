package br.com.passaporteclio.exception.handler;

import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.passaporteclio.exception.ExceptionResponse;
//import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.exception.InvalidJwtAuthenticationException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> allExceptionHandler(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(OffsetDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> invalidJwtAuthenticationException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(OffsetDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public final ResponseEntity<ExceptionResponse> resourceNotFoundException(Exception ex, WebRequest request) {
//		ExceptionResponse exceptionResponse = new ExceptionResponse(OffsetDateTime.now(), ex.getMessage(),
//				request.getDescription(false));
//		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//	}
}