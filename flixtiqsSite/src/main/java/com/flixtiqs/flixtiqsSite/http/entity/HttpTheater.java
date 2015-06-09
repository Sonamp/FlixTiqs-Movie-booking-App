package com.flixtiqs.flixtiqsSite.http.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;

@XmlRootElement(name = "theater")
public class HttpTheater {
	@XmlElement
	public long theaterId;
	@XmlElement
	public String name;
	@XmlElement
	public String city;
	@XmlElement
	public String state;
	@XmlElement
	public String zipcode;
	@XmlElement
	public List<HttpShow> movieShow;
	@XmlElement
	public boolean isdeleted;
	protected HttpTheater(){};
	
	
	public HttpTheater(Theater theater)
	{
		this.theaterId = theater.getTheaterId();
		this.name = theater.getName();
		this.city = theater.getCity();
		this.state = theater.getState();
		this.zipcode = theater.getZipcode();
		this.isdeleted = theater.isDeleted();
		this.movieShow = ConvertShowList(theater.getPlayingMovies());
	}
	
	public HttpTheater(Theater theater, List<MovieShow> playingMovies)
	{
		this.theaterId = theater.getTheaterId();
		this.name = theater.getName();
		this.city = theater.getCity();
		this.state = theater.getState();
		this.zipcode = theater.getZipcode();
		this.isdeleted = theater.isDeleted();
		this.movieShow = ConvertShowList(playingMovies);
	}
	
	private List<HttpShow> ConvertShowList(List<MovieShow> playingMovies)
	{
		List<HttpShow> newList = new ArrayList<HttpShow>();
		for(MovieShow show : playingMovies)
		{
			newList.add(new HttpShow(show));
		}
		return newList;
	}
	
}
