package com.flixtiqs.flixtiqsSite.entity.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;

/**
 * Implementation of theater interface
 * @author sonam
 *
 */
@Component
public class TheaterImpl implements Theater{

	private int theaterId;
	private String name;
	private String city;
	private String state;
	private String zipcode;
	//List of playing movies of type movie show
	private List<MovieShow> playingMovies = new ArrayList<MovieShow>();
	
	@Autowired
	MovieShow movieShow;
	
	public TheaterImpl()
	{
		this.theaterId = 1;
		this.name = "AMC Marcado";
		this.city = "Santa Clara";
		this.state = "CA";
		this.zipcode = "95054";				
	}
	
	public int getTheaterId() {
		// TODO Auto-generated method stub
		return this.theaterId;
	}
	public void setTheaterId(int id)
	{
		this.theaterId = id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public void setName(String name)
	{
		this.name =  name;
	}

	public String getState() {
		return this.state;
	}
	public void setState(String state)
	{
		this.state =  state;
	}
	
	public String getZipcode() {
		return this.zipcode;
	}
	public void setZipcode(String zip)
	{
		this.zipcode =  zip;
	}

	public String getCity() {
		return this.city;
	}
	public void setCity(String city)
	{
		this.city =  city;
	}

	public List<MovieShow> getPlayingMovies() {	
		//Add movie show to the list
		this.playingMovies.add(movieShow);
		return this.playingMovies;
	}
	
	public void setPlayingMovies(List<MovieShow> movies) {		
		this.playingMovies = movies;
	}


}
