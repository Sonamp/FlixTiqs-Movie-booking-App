package com.flixtiqs.flixtiqsSite.http.entity;

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
	public Date releasedDate;
	@XmlElement
	public int length;
	@XmlElement
	public double rating;
	
	protected HttpMovie() { };
	
	public HttpMovie(Movie movie)
	{
		this.movieId = movie.getMovieId();
		this.name =  movie.getName();
		this.releasedDate = movie.getReleasedDate();
		this.length = movie.getLength();
		this.rating = movie.getRating();
	}

}
