package com.flixtiqs.flixtiqsSite.http.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieImpl;

@XmlRootElement(name = "movieShow")
public class HttpShow {
	@XmlElement
	public long showId;
	@XmlElement
	public double price;
	@XmlElement
	public String showTime;
	@XmlElement
	public HttpMovie movie;
	@XmlElement
	public long movieId;
	@XmlElement
	public long theaterId;
	
	protected HttpShow(){};
	
	public HttpShow(MovieShow show)
	{
		this.showId =  show.getShowId();
		this.showTime = show.getShowTime();
		this.price = show.getPrice();
		this.movie = ConvertMovie(show.getMovie());
	}
	public HttpShow(MovieShow show, boolean flag)
	{
		this.showId =  show.getShowId();
		this.showTime = show.getShowTime();
		this.price = show.getPrice();
		this.movie = ConvertMovie(show.getMovie());
		this.movieId = show.getMovie().getMovieId();
		this.theaterId = show.getTheater().getTheaterId();
	}
	
	private HttpMovie ConvertMovie(Movie movie)
	{
		HttpMovie newMovie = new HttpMovie(movie);
		return newMovie;
	}

}
