package com.seneca.moviebinge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("Movie")
public class Movie {

	@Transient
    public static final String SEQUENCE_NAME = "movies_sequence";

    
	@Id
	public Long id;
	
	public String title;
	public String image;
	public Boolean isCarusel;
	public Boolean isFeatured;
	public String type;
	public String[] genre;
	public String duration;
	public Float imdbrating;
	public Float rentPrice;
	public Float buyPrice;
	public Integer releaseyear;
	public String backgroundimage;
	public String description; 
	
}
