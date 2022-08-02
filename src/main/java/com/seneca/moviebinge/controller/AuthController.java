package com.seneca.moviebinge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seneca.moviebinge.model.MovieBingeResponse;
import com.seneca.moviebinge.model.User;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping(value="/auth",consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MovieBingeResponse<User>> login(@RequestBody User user) {
		
		MovieBingeResponse<User> response=null;
		ResponseEntity<MovieBingeResponse<User>> responseEntity=null;
		try {
			System.out.println("Nirmal");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
			response= new MovieBingeResponse<>("User login Successfully",null);
			System.out.println("Nirmal");
			responseEntity=new ResponseEntity<MovieBingeResponse<User>>(response,HttpStatus.OK);
		}catch(BadCredentialsException e){
			response= new MovieBingeResponse<>("You entered wrong username/passwword",null);
			responseEntity=new ResponseEntity<MovieBingeResponse<User>>(response,HttpStatus.UNAUTHORIZED);	
		}
		return responseEntity;
		
		
	}
}
