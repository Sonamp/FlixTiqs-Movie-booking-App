package com.flixtiqs.flixtiqsSite.entity.impl;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

import com.flixtiqs.flixtiqsSite.entity.Movie;

/**
 * Implementation of Movie interface
 * @author sonam
 *
 */
@Component
public class MovieImpl implements Movie {
	private int movieId;
	private String name;
	private Date releasedDate;
	private int length;
	private double rating;
    
	public MovieImpl()
	{
		this.movieId = 1;
		this.name = "AvengerReturn";
		this.releasedDate = new GregorianCalendar(2015, 5, 01).getTime();
		this.length =  120;
		this.rating = 4.0;
	}
	
	public int getMovieId() {
		// TODO Auto-generated method stub
		return this.movieId;
	}
	public void setMovieId(int id) {
		// TODO Auto-generated method stub
		this.movieId = id;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public void  setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public Date getReleasedDate() {
		// TODO Auto-generated method stub
		return this.releasedDate;
	}
	public void  setreleasedDate(Date date) {
		// TODO Auto-generated method stub
		this.releasedDate = date;
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return this.length;
	}
	public void  setLength(int length) {
		// TODO Auto-generated method stub
		this.length = length;
	}

	public double getRating() {
		// TODO Auto-generated method stub
		return this.rating;
	}
	public void  setRating(double rating) {
		// TODO Auto-generated method stub
		this.rating = rating;
	}

	

}
