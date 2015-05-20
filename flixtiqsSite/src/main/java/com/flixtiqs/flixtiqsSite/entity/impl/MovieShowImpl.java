package com.flixtiqs.flixtiqsSite.entity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;

/**
 * Implementation of MovieShow interface extending movie implementation class
 * @author sonam
 *
 */
@Entity
@Table(name = "movie_show")
public class MovieShowImpl implements MovieShow {
	
	@Id
	@Column(name="idmovieshow")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long showId;
	@Column(name="price")
	private double price;
	@Column(name ="showtime")
	private String showTime;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=MovieImpl.class)
	@JoinColumn(name="Movie_idMovie")
	private Movie movie;
	
	@ManyToOne(fetch= FetchType.EAGER, targetEntity=TheaterImpl.class)
	@JoinColumn(name= "theater_idtheater")
	private Theater theater;
	
	//constructor 
	public MovieShowImpl()
	{
//		super();
//		this.price =  10.0;
//		this.showTime = new String[]{"10:30AM", "2.15PM", "4.00PM", "8.00PM" };
	}
	
	public double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getShowTime() {
		// TODO Auto-generated method stub
		return this.showTime;
	}
	public void setShowTime(String show)
	{
		this.showTime = show;
	}

	@Override
	public Movie getMovie() {		
		return this.movie;
	}
	public void setMovie(Movie movie)
	{
		this.movie =  movie;
	}

	@Override
	public Theater getTheater() {
		return this.theater;
	}
	public void setTheater(Theater theater)
	{
		this.theater =  theater;
	}

	@Override
	public long getShowId() {
		// TODO Auto-generated method stub
		return this.showId;
	}
	public void setShowId(long id)
	{
		this.showId = id;
	}
}
