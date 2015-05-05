package com.flixtiqs.flixtiqsSite.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieImpl;
import com.flixtiqs.flixtiqsSite.repository.MovieRepository;
import com.flixtiqs.flixtiqsSite.repository.impl.MovieRepositoryImpl;
import com.flixtiqs.flixtiqsSite.service.MovieService;

/**
 * Implementation of movie service interface
 * @author sonam
 *
 */
@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired	
	private MovieRepository movieRepo;
	@Override
	@Transactional
	public Movie getMovie(String name) {
		return movieRepo.getMovie(name);
	}
	@Override
	@Transactional
	public List<Movie> getMovieAfterReleasedDate(Date date) {
		List<Movie> movieList = movieRepo.getMovieAfterReleasedDate(date);
		return movieList;
	}

	@Override
	@Transactional
	public Movie addMovie(Movie movie) {
		long id = movieRepo.addMovie(movie); 
		return movieRepo.getMovie(id);
	}
	@Override
	@Transactional
	public Movie getMovie(long id) {		
		return movieRepo.getMovie(id);
	}
	
	
	@Override
	@Transactional
	public List<Movie> getAllMovie() {		
		return movieRepo.getAllMovie();
	}
	@Override
	@Transactional
	public void update(Movie movie) {
		movieRepo.update(movie);
		
	}
}


