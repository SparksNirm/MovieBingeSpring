package com.seneca.moviebinge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Document(collection = "movie_sequences")
@Getter
@Setter
public class DatabaseMovieSequence {

	  @Id
	    private String id;

	    private long seq;
}
