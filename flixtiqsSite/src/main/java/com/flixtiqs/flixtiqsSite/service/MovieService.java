package com.flixtiqs.flixtiqsSite.service;

import java.util.Date;
import java.util.List;

import com.flixtiqs.flixtiqsSite.entity.Movie;
/**
 * MovieService interface to seach the movies based on name, released date
 * @author sonam
 *
 */
public interface MovieService {

	/**
	 * get movies from database based on movie id
	 */
	Movie getMovie(long id);
	/**
	 * search movie by name
	 */
	List<Movie> getMovie(String name);
	/**
	 * search movie which are released after given date
	 */
	List<Movie> getMovieAfterReleasedDate(Date date);
	/**
	 * return all movies
	 */
	List<Movie> getAllMovie();
	/**
	 * Add movie to database
	 */
	Movie addMovie(Movie movie);
	/**
	 * update movie to the database
	 */
	void update(Movie movie);
	
	void delete(Movie movie);
}
