package com.flixtiqs.flixtiqsSite.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieImpl;
import com.flixtiqs.flixtiqsSite.service.MovieService;

/**
 * Implementation of movie service interface
 * @author sonam
 *
 */
@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	@Qualifier("movieImpl")
	private MovieImpl movie;

	public Movie getMovie(String name) {
		// TODO Auto-generated method stub
		if(movie.getName().equals(name))
			return movie;
		else
			return null;
	}

	public List<Movie> getMovieAfterReleasedDate(Date date) {
		List<Movie> movieList = new ArrayList<Movie>();
		if(movie.getReleasedDate().compareTo(date) >= 0){
			movieList.add(movie);			
		}
		
		return movieList;
	}

	public List<Movie> getAllMovie() {
		// TODO Auto-generated method stub
		List<Movie> movieList = new ArrayList<Movie>();
		movieList.add(movie);
		return movieList;
	}
}


