package com.seneca.moviebinge.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.seneca.moviebinge.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {

	public List<Movie> findByType(String type);
	
	@Query("{$and:[{isCarusel:?0},{title:{$regex:?1,$options: 'i'}}]}")
	public List<Movie> getMoviesByTitle(Boolean isCarusel,String title);
	
	
	@Query("{$and:[{type:?0},{isFeatured:?1}]}")
	public List<Movie> getFeaturedMovies(String type,Boolean isFeatured);
	
	@Query("{isCarusel:?0}")
	public List<Movie> getCaursel(Boolean isCarusel);
	
	@Query("{$or:[{type:?0},{type:?1}]}")
	public List<Movie> getAllShows(String type,String type_1);
	
}
