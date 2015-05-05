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
	double getPrice();
	String getShowTime();
	Movie getMovie();
	Theater getTheater();
	
}
