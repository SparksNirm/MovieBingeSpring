package com.seneca.moviebinge.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("User")
public class User {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	public Long id;
	public String lastName;
	public String firstName;
	public String userName;
	public String password;
	public String mobileNumber;
	public String emailAddress;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", userName=" + userName
				+ ", password=" + password + ", mobileNumber=" + mobileNumber + ", emailAddress=" + emailAddress + "]";
	}
	
	

}
