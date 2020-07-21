package almeida.ferreira.junio.bluetasks.infrastructure.web;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import almeida.ferreira.junio.bluetasks.application.handler.DuplicateTaskException;

@RestControllerAdvice
public class WebRequestExceptionHandler {
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestValidationError handleException(RepositoryConstraintViolationException e) {
		return RestValidationError.fromValidationError(e.getErrors());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestValidationError handleException(DuplicateTaskException e) {
		return RestValidationError.fromMessage(e.getMessage());
	}
}
