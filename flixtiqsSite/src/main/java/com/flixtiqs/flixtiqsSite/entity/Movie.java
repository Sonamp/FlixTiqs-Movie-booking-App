package com.flixtiqs.flixtiqsSite.entity;

import java.util.Date;
import java.util.List;
/**
 * Movie interface
 * @author sonam
 *
 */
public interface Movie {
	long getMovieId();
	String getName();
	Date getReleasedDate();
	int getLength();
	double getRating();
	String getCategory();
	boolean isDeleted();
	List<MovieShow> getMovieInTheater();
	void addMovieInTheater(MovieShow movieShow);
}
