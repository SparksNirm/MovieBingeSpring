package com.seneca.moviebinge.service;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.seneca.moviebinge.model.DatabaseMovieSequence;
import com.seneca.moviebinge.model.DatabaseUserSequence;


@Service
public class SequenceGeneratorService {

	private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequenceUser(String seqName) {

        DatabaseUserSequence counter = mongoOperations.findAndModify(query(where ("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseUserSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
    
    public long generateSequenceMovie(String seqName) {

        DatabaseMovieSequence counter = mongoOperations.findAndModify(query(where ("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseMovieSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }
}
