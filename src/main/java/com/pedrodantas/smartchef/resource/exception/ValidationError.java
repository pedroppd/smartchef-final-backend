package com.pedrodantas.smartchef.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> list = new ArrayList<>();
	
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);	
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String msg) {
		list.add(new FieldMessage(fieldName, msg));
	}
	
	

	
}
