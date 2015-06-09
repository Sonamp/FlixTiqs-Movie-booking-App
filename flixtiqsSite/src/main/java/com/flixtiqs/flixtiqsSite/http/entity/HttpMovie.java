package com.flixtiqs.flixtiqsSite.http.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.flixtiqs.flixtiqsSite.entity.Movie;

@XmlRootElement(name = "movie")
public class HttpMovie {
	
	@XmlElement
	public long movieId;
	@XmlElement
	public String name;
	@XmlElement
	public String releasedDate;
	@XmlElement
	public int length;
	@XmlElement
	public double rating;
	@XmlElement
	public String category;
	@XmlElement
	public boolean isdeleted;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	protected HttpMovie() { };
	
	public HttpMovie(Movie movie)
	{
		this.movieId = movie.getMovieId();
		this.name =  movie.getName();
		this.releasedDate = df.format(movie.getReleasedDate());		
		this.length = movie.getLength();
		this.rating = movie.getRating();
		this.category = movie.getCategory();
		this.isdeleted = movie.isDeleted();
	}

}
