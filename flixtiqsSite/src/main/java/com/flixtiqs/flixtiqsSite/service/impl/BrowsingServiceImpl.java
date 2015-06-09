package com.flixtiqs.flixtiqsSite.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
import com.flixtiqs.flixtiqsSite.entity.impl.TheaterImpl;
import com.flixtiqs.flixtiqsSite.repository.TheaterRepository;
import com.flixtiqs.flixtiqsSite.service.BrowsingService;
import com.flixtiqs.flixtiqsSite.service.exception.FieldsException;;

/**
 * Implementation of Browsing service interface
 * @author sonam
 *
 */
@Service
public class BrowsingServiceImpl implements BrowsingService {
	@Autowired
	private TheaterRepository theaterRepo;
	@Override
	@Transactional
	public List<Theater> getTheaterByName(String name) {		
		return theaterRepo.getTheater(name);
	}
	@Override
	@Transactional
	public List<Theater> getTheaterByCityState(String city, String state) {		
		return theaterRepo.getTheater(city, state);
	}
	@Override
	@Transactional
	public List<Theater> getTheaterByZipcode(String zipcode) {		
		return theaterRepo.getTheaterByZip(zipcode);
	}

	@Override
	@Transactional
	public Theater addTheater(Theater theater) {
		long id = 0;
		try
		{
			id = theaterRepo.addTheater(theater);
		}
		catch(Exception e)
		{
			throw new FieldsException("Theater not added "+ theater);
		}
		return theaterRepo.getTheater(id);
	}
	@Override
	@Transactional
	public Theater getTheater(long id) {	
		Theater found = theaterRepo.getTheater(id);
		if(found == null)
		{
			throw new FieldsException("Theater not found "+ id);
		}
		return found;
	}
	@Override
	@Transactional
	public void update(Theater theater) {
		theaterRepo.update(theater);
		
	}
	@Override
	@Transactional
	public List<MovieShow> getMovieShow(Movie movie, Theater theater) {		
		return theaterRepo.getMovieShow(movie, theater);
	}
	@Override
	@Transactional
	public List<Theater> getTheaterForMovie(Movie movie) {
		return theaterRepo.getTheaterForMovie(movie);
	}
	@Override
	@Transactional
	public List<MovieShow> getPlayingMovieShows(Theater theater) {
		return theaterRepo.getPlayingMovieShows(theater);
	}
	@Override
	@Transactional
	public List<Movie> getPlayingMovies(Theater theater) {
		return theaterRepo.getPlayingMovies(theater);
	}
	@Override
	@Transactional
	public List<Theater> getAllTheater() {		
		return theaterRepo.getAllTheaters();
	}
	@Override
	@Transactional
	public MovieShow getMovieShowById(long id) {
		return theaterRepo.getMovieShowById(id);
	}
}
