package com.seneca.moviebinge.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.seneca.moviebinge.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long>{

	public Optional<User> findByUserName(String userName);
	
	public Optional<User> findByEmailAddress(String emailAddress);
}
