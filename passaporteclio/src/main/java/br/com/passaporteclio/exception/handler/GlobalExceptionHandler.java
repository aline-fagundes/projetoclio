package br.com.passaporteclio.exception.handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.passaporteclio.exception.ExceptionResponse;
import br.com.passaporteclio.exception.ExceptionResponseDto;
//import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.exception.InvalidJwtAuthenticationException;
import br.com.passaporteclio.exception.OperationNotAllowedException;
import br.com.passaporteclio.exception.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> allExceptionHandler(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(OffsetDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> invalidJwtAuthenticationException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(OffsetDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> resourceNotFoundException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(OffsetDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OperationNotAllowedException.class)
	public final ResponseEntity<ExceptionResponse> OperationNotAllowedException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(OffsetDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ExceptionResponseDto> handle(MethodArgumentNotValidException exception) {
		List<ExceptionResponseDto> errosDto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ExceptionResponseDto erro = new ExceptionResponseDto(e.getField(), mensagem);
			errosDto.add(erro);
		});
		return errosDto;
	}
}