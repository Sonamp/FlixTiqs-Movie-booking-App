package com.flixtiqs.flixtiqsSite.entity;

import java.util.Date;
import java.util.List;
/**
 * Movie interface
 * @author sonam
 *
 */
public interface Movie {
	int getMovieId();
	String getName();
	Date getReleasedDate();
	int getLength();
	double getRating();
}
