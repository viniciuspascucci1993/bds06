package com.devsuperior.movieflix.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.movieflix.services.exceptions.DatabaseIntegrityException;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnAuthorizedException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFoundException( ResourceNotFoundException e , HttpServletRequest request ) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError standardError = new StandardError();
		standardError.setTimeStamp(Instant.now());
		standardError.setStatus(status.value());
		standardError.setError("Resource Not Found");
		standardError.setMessage(e.getMessage());
		standardError.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler(DatabaseIntegrityException.class)
	public ResponseEntity<StandardError> dataBaseIntegrityException( DatabaseIntegrityException e , HttpServletRequest request ) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError standardError = new StandardError();
		standardError.setTimeStamp(Instant.now());
		standardError.setStatus(status.value());
		standardError.setError("Database Exception");
		standardError.setMessage(e.getMessage());
		standardError.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
	}
	
	// MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation( MethodArgumentNotValidException e , HttpServletRequest request ) {
		
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		ValidationError validationError = new ValidationError();
		validationError.setTimeStamp(Instant.now());
		validationError.setStatus(status.value());
		validationError.setError("Valtidation Exception");
		validationError.setMessage(e.getMessage());
		validationError.setPath(request.getRequestURI());
		
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(validationError);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<AuthorizationCustomError> forbiddenException( ForbiddenException e , HttpServletRequest request ) {
		
		HttpStatus status = HttpStatus.FORBIDDEN;
		
		AuthorizationCustomError error = new AuthorizationCustomError();
		
		error.setError("FORBIDDEN");
		error.setErrorDescription(e.getMessage());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<AuthorizationCustomError> unAuthorizedException( UnAuthorizedException e , HttpServletRequest request ) {
		
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		AuthorizationCustomError error = new AuthorizationCustomError();
		
		error.setError("UNAUTHORIZED");
		error.setErrorDescription(e.getMessage());
		
		return ResponseEntity.status(status).body(error);
	}
}
