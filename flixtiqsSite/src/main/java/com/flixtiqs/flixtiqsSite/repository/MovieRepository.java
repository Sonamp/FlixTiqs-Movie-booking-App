package com.flixtiqs.flixtiqsSite.repository;

import java.util.Date;
import java.util.List;

import com.flixtiqs.flixtiqsSite.entity.Movie;

public interface MovieRepository {
	
	long addMovie(Movie movie);
	Movie getMovie(long movieId);
	List<Movie> getMovie(String movieName);
	List<Movie> getMovieAfterReleasedDate(Date date);
	List<Movie> getAllMovie();
	void update(Movie movie);
	void delete(Movie movie);
}
