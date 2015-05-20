package com.flixtiqs.flixtiqsSite.service.exception;

@SuppressWarnings("serial")
public class FieldsException extends FlixtiqsException{

	public FieldsException(String message, Throwable throwable) {
		super(ErrorCode.INVALID_FIELD, message, throwable);
	}
	
	public FieldsException(String message) {
		super(ErrorCode.INVALID_FIELD, message);
	}
}
