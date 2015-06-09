package com.flixtiqs.flixtiqsSite.entity.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.MovieShow;

/**
 * Implementation of Movie interface
 * @author sonam
 *
 */
@Entity
@Table(name = "Movie")
public class MovieImpl implements Movie {
	@Id
	@Column(name = "idMovie")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long movieId;
	@Column(name="name")
	private String name;
	@Column(name="released_date")
	private Date releasedDate;
	@Column(name="length")
	private int length;
	@Column(name="rating")
	private double rating;
	@Column(name="deleted")
	private int deleted;
	@Column(name="category")
	private String category;
    
	@OneToMany(mappedBy="movie", targetEntity=MovieShowImpl.class, cascade= CascadeType.ALL)
	private List<MovieShow> movieInTheaters;
	
	
	public MovieImpl()
	{
//		this.movieId = 1;
//		this.name = "AvengerReturn";
//		this.releasedDate = new GregorianCalendar(2015, 5, 01).getTime();
//		this.length =  120;
//		this.rating = 4.0;
	}
	@Override
	public long getMovieId() {
		// TODO Auto-generated method stub
		return this.movieId;
	}
	public void setMovieId(long id) {
		// TODO Auto-generated method stub
		this.movieId = id;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public void  setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	@Override
	public Date getReleasedDate() {
		// TODO Auto-generated method stub
		return this.releasedDate;
	}
	public void  setreleasedDate(Date date) {
		// TODO Auto-generated method stub
		this.releasedDate = date;
	}
	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return this.length;
	}
	public void  setLength(int length) {
		// TODO Auto-generated method stub
		this.length = length;
	}
	@Override
	public double getRating() {
		// TODO Auto-generated method stub
		return this.rating;
	}
	public void  setRating(double rating) {
		// TODO Auto-generated method stub
		this.rating = rating;
	}
	@Override
	public String toString()
	{
		return "Movie [id =" + movieId +", Name = " + name + ", Length= " + length + " Min, Released date = " + releasedDate + ", Rating = " + rating + " ]";
	}
	@Override
	public List<MovieShow> getMovieInTheater() {
		return this.movieInTheaters;
	}
	@Override
	public void addMovieInTheater(MovieShow movieShow) {
		if(this.movieInTheaters == null)
			this.movieInTheaters = new ArrayList<MovieShow>();
		this.movieInTheaters.add(movieShow);
		
	}
	@Override
	public boolean isDeleted() {	
		if(deleted == 0)
			return false;
		else
			return true;
	}
	public void setDeleted(boolean deleted){
		if(deleted)
			this.deleted = -1;
		else
			this.deleted = 0;
	}
	@Override
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category){
		this.category = category;
	}
}
