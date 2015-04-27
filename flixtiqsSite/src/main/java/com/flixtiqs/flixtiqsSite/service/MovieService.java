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

	Movie getMovie(String name);
	List<Movie> getMovieAfterReleasedDate(Date date);
	List<Movie> getAllMovie();
}
