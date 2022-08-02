package com.seneca.moviebinge.exception;

public class InValidRequest extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	public InValidRequest() {
		
	}
	public InValidRequest(String message) {
		super(message);
	}

}
