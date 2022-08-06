package com.seneca.moviebinge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seneca.moviebinge.constant.MovieConstant;
import com.seneca.moviebinge.model.Movie;
import com.seneca.moviebinge.repo.MovieRepository;

@Service
public class MovieService {

	@Autowired
	public MovieRepository movieRepository;
	
	@Autowired
	public SequenceGeneratorService sequenceGeneratorService;
	
	public Movie createMovie(Movie movie){
		
		movie.setId(sequenceGeneratorService.generateSequenceMovie(Movie.SEQUENCE_NAME));
		Movie newMovie=movieRepository.save(movie);
		return newMovie;
	}
	
	public Movie getMovieById(Long id) throws Exception{
		
		Optional<Movie> optionalMovie=movieRepository.findById(id);
		Movie movie=null;
		if(optionalMovie.isPresent())
		{
			movie=optionalMovie.get();
		}else
		{
			throw new Exception("No Movie Found with id:"+id);
		}
		return movie;
	}
	
	
	public List<Movie> getMovieByType(String type) {
		List<Movie> movies= movieRepository.findByType(type);
		return movies;
	}
	
	public List<Movie> searchMovieByTitle(String title){
		List<Movie> movies = movieRepository.getMoviesByTitle(false,title);
		return movies;
	}
	
	public List<Movie> getFeaturedMovie()
	{
		List<Movie> movies = movieRepository.getFeaturedMovies(MovieConstant.MOVIE_TYPE, true);
		System.out.println(movies);
		return movies;
		
	}
	public List<Movie> getCaursel()
	{
		List<Movie> movies = movieRepository.getCaursel(true);
		System.out.println(movies);
		return movies;
		
	}
	public List<Movie> getFeaturedTvShows()
	{
		List<Movie> movies = movieRepository.getFeaturedMovies(MovieConstant.TVSHOWS_TYPE, true);
		System.out.println(movies);
		return movies;
		
	}
	
	
	public Movie updateMovie(Long id,Movie movie) throws Exception
	{
		Movie movieDb=null;
		Optional<Movie> optionalMovie = movieRepository.findById(id);
		if(optionalMovie.isPresent())
		{
			movieDb=optionalMovie.get();
			movieDb.setTitle(movie.getTitle());
			movieDb.setImage(movie.getImage());
			movieDb.setIsCarusel(movie.getIsCarusel());
			movieDb.setIsFeatured(movie.getIsFeatured());
			movieDb.setType(movie.getType());
			movieDb.setGenre(movie.getGenre());
			movieDb.setDuration(movie.getDuration());
			movieDb.setImdbrating(movie.rentPrice);
			movieDb.setRentPrice(movie.getRentPrice());
			movieDb.setBuyPrice(movie.getBuyPrice());
			movieDb.setReleaseyear(movie.getReleaseyear());
			movieDb.setDescription(movie.getDescription());
			movieDb.setBackgroundimage(movie.getBackgroundimage());
			movieRepository.save(movieDb);
		}else
		{
			throw new Exception("No Movie Found for Id: "+id);
		}
		
		
		return movieDb;
	}
	
	
	public Long deleteMovie(Long Id) throws Exception
	{
		Optional<Movie> movie = movieRepository.findById(Id);
		if(movie.isPresent())
		{
			movieRepository.deleteById(Id);

		}else
		{
			throw new Exception("No Movie Deleted");
		}
		return Id;
	}
	
}
