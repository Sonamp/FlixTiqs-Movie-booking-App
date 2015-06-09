package com.flixtiqs.flixtiqsSite.entity;

import java.util.List;
/**
 * MovieShow interface extending Movie interface
 * This interface theater specific ticket price and show timings for movies
 * @author sonam
 *
 */
public interface MovieShow {
	//Movie getMovie();
	long getShowId();
	double getPrice();
	String getShowTime();
	Movie getMovie();
	Theater getTheater();
	boolean isDeleted();
	int getSeatsAvail();
}
