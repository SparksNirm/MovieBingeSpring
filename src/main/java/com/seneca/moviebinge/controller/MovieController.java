package com.seneca.moviebinge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seneca.moviebinge.constant.MovieConstant;
import com.seneca.moviebinge.model.Movie;
import com.seneca.moviebinge.model.MovieBingeResponse;
import com.seneca.moviebinge.service.MovieService;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins={"http://localhost:3000","https://moviebinge.herokuapp.com/"})
public class MovieController {

	@Autowired
	public MovieService movieService;

	
	@PostMapping("/createmovies")
	public ResponseEntity<MovieBingeResponse<Movie>> creatMovie(@RequestBody Movie movie){
		MovieBingeResponse<Movie> response=null;
		List<Movie> movies=new ArrayList<>();
		Movie newMovie=movieService.createMovie(movie);
		movies.add(newMovie);
		response=new MovieBingeResponse<Movie>("Movie Created Successfully",movies);
		return new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/movies/{id}")
	public ResponseEntity<MovieBingeResponse<Movie>> getMovieById(@PathVariable Long id){
		Movie movie=null;
		ResponseEntity<MovieBingeResponse<Movie>> responseEntity=null;
		MovieBingeResponse<Movie> response=null;
		List<Movie> movies=new ArrayList<>();
		try {
			movie=movieService.getMovieById(id);
			movies.add(movie);
			response=new MovieBingeResponse<Movie>("Movie Retrived Successfully with Id: "+movie.getId(),movies);
			responseEntity=new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response=new MovieBingeResponse<Movie>(e.getMessage(),null);
			responseEntity=new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.NOT_FOUND);
		}
		return responseEntity; 
	}
	
	
	@GetMapping("/movies")
	public ResponseEntity<MovieBingeResponse<Movie>> getMovie(){
		List<Movie> movies=null;
		MovieBingeResponse<Movie> response=null;
		movies=movieService.getMovieByType(MovieConstant.MOVIE_TYPE);
		response=new MovieBingeResponse<Movie>("All Movies Retrived Successfully",movies);
		return new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/tvshows")
	public ResponseEntity<MovieBingeResponse<Movie>> getTVShows(){
		List<Movie> movies=null;
		MovieBingeResponse<Movie> response=null;
		movies=movieService.getMovieByType(MovieConstant.TVSHOWS_TYPE);
		response=new MovieBingeResponse<Movie>("All TV Shows Retrived Successfully",movies);
		return new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
	}

	@GetMapping("/searchtitle/{title}")
	public ResponseEntity<MovieBingeResponse<Movie>> getMoviesOrTvShows(@PathVariable String title){
		List<Movie> movies=null;;
		MovieBingeResponse<Movie> response=null;
		movies=movieService.searchMovieByTitle(title);
		response=new MovieBingeResponse<Movie>("Search by Title is Successfull",movies);
		return new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/featuredmovie")
	public ResponseEntity<MovieBingeResponse<Movie>> getfeaturedMovie(){
		List<Movie> movies=null;
		MovieBingeResponse<Movie> response=null;
		movies=movieService.getFeaturedMovie();
		response=new MovieBingeResponse<Movie>("Featured Movies Retrived Successfully",movies);
		return new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/featuredtvshows")
	public ResponseEntity<MovieBingeResponse<Movie>> getfeaturedTvShows(){
		List<Movie> movies=null;
		MovieBingeResponse<Movie> response=null;
		movies=movieService.getFeaturedTvShows();
		response=new MovieBingeResponse<Movie>("Featured TV Shows Retrived Successfully",movies);
		return new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updatemovie/{id}")
	public ResponseEntity<MovieBingeResponse<Movie>> updateMovie(@PathVariable Long id,@RequestBody Movie newmovie){
		MovieBingeResponse<Movie> response=null;
		ResponseEntity<MovieBingeResponse<Movie>> responseEntity=null;
		List<Movie> movies= new ArrayList<Movie>();
		try {
			newmovie=movieService.updateMovie(id,newmovie);
			movies.add(newmovie);
			response=new MovieBingeResponse<Movie>("Movie with id: "+newmovie.getId()+" successfully Updated" ,movies);
			responseEntity=new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
		} catch (Exception e) {
		
			response=new MovieBingeResponse<Movie>(e.getMessage(),null);
			responseEntity=new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}
	
	@DeleteMapping("/deletemovie/{id}")
	public ResponseEntity<MovieBingeResponse<Movie>> deleteMovie(@PathVariable Long id){
		MovieBingeResponse<Movie> response=null;
		ResponseEntity<MovieBingeResponse<Movie>> responseEntity=null;
		Long Id;
		try {
			Id = movieService.deleteMovie(id);
			response=new MovieBingeResponse<Movie>("Movie with id: "+Id+" successfully Deleted" ,null);
			responseEntity=new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response= new MovieBingeResponse<Movie>(e.getMessage(),null);
			responseEntity=new ResponseEntity<MovieBingeResponse<Movie>>(response,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}
