package com.seneca.moviebinge.exception;

public class NotAValidEmail extends Exception {

	private static final long serialVersionUID = 1L;

	
	public NotAValidEmail(){
		
	}
	public NotAValidEmail(String message) {
		super(message);
	}
	
}
