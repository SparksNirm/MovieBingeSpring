package com.seneca.moviebinge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "user_sequences")
@Getter
@Setter
public class DatabaseUserSequence {

    @Id
    private String id;

    private long seq;
}