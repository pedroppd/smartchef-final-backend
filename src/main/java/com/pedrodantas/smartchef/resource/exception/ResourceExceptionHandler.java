package com.pedrodantas.smartchef.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.pedrodantas.smartchef.service.exception.DataIntegrityException;
import com.pedrodantas.smartchef.service.exception.FileException;
import com.pedrodantas.smartchef.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "erro de validação: ", e.getMessage(), request.getRequestURI());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "erro de validação: ", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		HttpStatus cod = HttpStatus.valueOf(e.getErrorCode());
		
		ValidationError err = new ValidationError(System.currentTimeMillis(), cod.value(), "erro de validação: ", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(cod).body(err);
	}
	
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
		
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "erro de validação: ", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
		
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "erro de validação: ", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
}
