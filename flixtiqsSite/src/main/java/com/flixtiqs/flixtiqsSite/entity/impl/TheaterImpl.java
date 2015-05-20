package com.flixtiqs.flixtiqsSite.entity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;

/**
 * Implementation of theater interface
 * @author sonam
 *
 */
@Entity
@Table(name="theater")
public class TheaterImpl implements Theater{
	@Id
	@Column(name = "idtheater")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long theaterId;
	@Column(name="name")
	private String name;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="zipcode")
	private String zipcode;
	
	//List of playing movies of type movie show
	@OneToMany(fetch=FetchType.EAGER, mappedBy="theater", targetEntity=MovieShowImpl.class, cascade=CascadeType.ALL)
	private List<MovieShow> playingMovies = new ArrayList<MovieShow>();
	
	//@Autowired
	//MovieShow movieShow;
	
	public TheaterImpl()
	{
//		this.theaterId = 1;
//		this.name = "AMC Marcado";
//		this.city = "Santa Clara";
//		this.state = "CA";
//		this.zipcode = "95054";				
	}
	
	public long getTheaterId() {
		// TODO Auto-generated method stub
		return this.theaterId;
	}
	public void setTheaterId(long id)
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

	@Override
	public String toString()
	{
		return "Theater [ id =" + this.theaterId+ ", Name = " + this.name + " City = " + this.city + " State = " + this.state +  " Zipcode = " + this.zipcode + " ]";
	}
//	public List<MovieShow> getPlayingMovies() {	
//		//Add movie show to the list
//		this.playingMovies.add(movieShow);
//		return this.playingMovies;
//	}
//	
//	public void setPlayingMovies(List<MovieShow> movies) {		
//		this.playingMovies = movies;
//	}

	@Override
	public List<MovieShow> getPlayingMovies() {
		return this.playingMovies;
	}

	@Override
	public void addPlayingMovies(MovieShow movieShow) {
		if(this.playingMovies == null)
			this.playingMovies = new ArrayList<MovieShow>();
		this.playingMovies.add(movieShow);
	}


}
