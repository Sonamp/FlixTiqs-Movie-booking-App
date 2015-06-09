package com.flixtiqs.flixtiqsSite.repository;

import java.util.List;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;

public interface TheaterRepository {

	long addTheater(Theater theater);
	Theater getTheater(long id);
	List<Theater> getTheater(String theaterName);
	List<Theater> getTheater(String city, String state);
	List<Theater> getTheaterByZip(String zipcode);
	List<Theater> getAllTheaters();
	void update(Theater theater);
	List<MovieShow>getMovieShow(Movie movie, Theater theater);
	List<Theater> getTheaterForMovie(Movie movie);
	List<MovieShow> getPlayingMovieShows(Theater theater);
	List<Movie> getPlayingMovies(Theater theater);
	void delete(Theater theater);
	MovieShow getMovieShowById(long id);
}
