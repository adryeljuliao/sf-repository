package com.sf.repo.api.services.execptions;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 7780178006963400508L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
