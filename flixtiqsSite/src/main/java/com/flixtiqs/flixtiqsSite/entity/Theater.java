package com.flixtiqs.flixtiqsSite.entity;

import java.util.List;
/**
 * Interface theater
 * @author sonam
 *
 */
public interface Theater {
	int getTheaterId();
	String getName();
	String getState();
	String getZipcode();
	String getCity();	
	List<MovieShow> getPlayingMovies();
}
