package com.flixtiqs.flixtiqsSite.service;

import java.util.List;

import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
/**
 * Browsing service interface for browsing the theaters based on location and playing movies in the theater
 * @author sonam
 *
 */
public interface BrowsingService {
	Theater getTheaterByName(String name);
	List<Theater> getTheaterByCityState(String city, String state);
	List<Theater> getTheaterByZipcode(String zipcode);
}
