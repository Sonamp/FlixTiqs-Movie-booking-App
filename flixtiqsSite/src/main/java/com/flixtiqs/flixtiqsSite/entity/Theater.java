package com.flixtiqs.flixtiqsSite.entity;

import java.util.List;
/**
 * Interface theater
 * @author sonam
 *
 */
public interface Theater {
	long getTheaterId();
	String getName();
	String getState();
	String getZipcode();
	String getCity();	
	boolean isDeleted();
	List<MovieShow> getPlayingMovies();
	void addPlayingMovies(MovieShow movieShow);
	void updatePlayingMovie(MovieShow movieShow);
}
