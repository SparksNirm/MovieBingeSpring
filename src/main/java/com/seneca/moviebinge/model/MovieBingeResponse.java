package com.seneca.moviebinge.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieBingeResponse<T> {
	
	private String message;
	private List<T> body;
	
	public MovieBingeResponse(String message, List<T> body) {
		this.message = message;
		this.body = body;
	}
	
	
	public MovieBingeResponse() {
		
	}
	
	

}
