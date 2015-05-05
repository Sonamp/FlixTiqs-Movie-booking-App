package com.flixtiqs.flixtiqsSite.service;

import java.util.List;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
/**
 * Browsing service interface for browsing the theaters based on location and playing movies in the theater
 * @author sonam
 *
 */
public interface BrowsingService {
	/**
	 * Add theater to the database
	 */
	Theater addTheater(Theater theater);
	/**
	 * search theater based on theater id
	 */
	Theater getTheater(long id);
	/**
	 * search theater by name
	 */
	List<Theater> getTheaterByName(String name);
	/**
	 * search theater by location city+state
	 */
	List<Theater> getTheaterByCityState(String city, String state);
	/**
	 * search theater by zipcode
	 */
	List<Theater> getTheaterByZipcode(String zipcode);
	/**
	 * update theater to the database
	 */
	void update(Theater theater);
	/**
	 * get movie shows in a theater for given movie
	 */
	List<MovieShow>getMovieShow(Movie movie, Theater theater);
	/**
	 * search all the theater who are playing the searched movie
	 */
	List<Theater> getTheaterForMovie(Movie movieId);
	/**
	 * get all movies shows in the searched theater
	 */
	List<MovieShow> getPlayingMovieShows(Theater theater);
	/**
	 * get only movies in the searched theater
	 */
	List<Movie> getPlayingMovies(Theater theater);
}
