package com.flixtiqs.flixtiqsSite.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
import com.flixtiqs.flixtiqsSite.entity.impl.TheaterImpl;
import com.flixtiqs.flixtiqsSite.service.BrowsingService;

/**
 * Implementation of Browsing service interface
 * @author sonam
 *
 */
@Service
public class BrowsingServiceImpl implements BrowsingService {
	@Autowired
	private TheaterImpl theater;
	
	public Theater getTheaterByName(String name) {		
		if(theater.getName().equals(name))
			return theater;
		else
			return null;
	}

	public List<Theater> getTheaterByCityState(String city, String state) {
		List<Theater> theaterList = new ArrayList<Theater>();
		if(theater.getCity().equals(city) && theater.getState().equals(state))
		{
			theaterList.add(theater);
		}
		return theaterList;
	}

	public List<Theater> getTheaterByZipcode(String zipcode) {
		List<Theater> theaterList = new ArrayList<Theater>();
		if(theater.getZipcode().equals(zipcode))
		{
			theaterList.add(theater);
		}
		return theaterList;
	}
}
