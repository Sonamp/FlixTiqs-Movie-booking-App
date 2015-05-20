package com.flixtiqs.flixtiqsSite.service.exception;

@SuppressWarnings("serial")
public class FlixtiqsException extends RuntimeException{

	private ErrorCode errorCode;

	public FlixtiqsException(ErrorCode code, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = code;
	}
	
	public FlixtiqsException(ErrorCode code, String message) {
		super(message);
		this.errorCode = code;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
