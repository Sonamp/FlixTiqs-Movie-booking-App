package com.flixtiqs.flixtiqsSite.http;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
import com.flixtiqs.flixtiqsSite.entity.impl.TheaterImpl;
import com.flixtiqs.flixtiqsSite.http.entity.HttpMovie;
import com.flixtiqs.flixtiqsSite.http.entity.HttpTheater;
import com.flixtiqs.flixtiqsSite.service.BrowsingService;
import com.flixtiqs.flixtiqsSite.service.MovieService;
import com.flixtiqs.flixtiqsSite.service.exception.FlixtiqsException;

@Path("/theaters")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class TheaterResource {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BrowsingService theaterService;
	@Autowired
	private MovieService movieService;
	
	@POST
	@Path("/")
	public Response createTheater(HttpTheater newTheater){
		Theater theater = convert(newTheater);
		Theater addedTheater = theaterService.addTheater(theater);
		return Response.status(Status.CREATED).header("Location", "/theaters/"+addedTheater.getTheaterId()).entity(new HttpTheater(addedTheater)).build();
	}
	
	@GET
	@Path("/{theaterId}")	
	public HttpTheater getTheaterById(@PathParam("theaterId") long theaterId){
		logger.info("getting theater by id:"+theaterId);
		Theater theater = theaterService.getTheater(theaterId);	
		List<MovieShow> showList = theaterService.getPlayingMovieShows(theater);
		return new HttpTheater(theater, showList);
	}
	
	@GET
	@Path("/")
	@Wrapped(element="theaters")
	public List<HttpTheater> getTheaterSearch(@QueryParam("name") String name, @QueryParam("city") String city, @QueryParam("state") String state, 
			@QueryParam("zip") String zipcode, @QueryParam("movie") Long movieId) throws FlixtiqsException {
		List<Theater> foundList= new ArrayList<Theater>();
		if(name != null)
		{
			foundList = theaterService.getTheaterByName(name);
		}
		else if(city != null || state != null)
		{
			foundList = theaterService.getTheaterByCityState(city, state);
		}
		else if(zipcode != null)
		{
			foundList = theaterService.getTheaterByZipcode(zipcode);
		}
		else if(movieId != null)
		{
			foundList = theaterService.getTheaterForMovie(movieService.getMovie(movieId));
		}
		
		List<HttpTheater> returnList = new ArrayList<>(foundList.size());
		if(movieId != null)
		{		
			for(Theater theater : foundList){
			List<MovieShow> showList = theaterService.getMovieShow(movieService.getMovie(movieId), theater);
			returnList.add(new HttpTheater(theater, showList));
			}
		}
		else {
			for(Theater theater : foundList){
				List<MovieShow> showList = theaterService.getPlayingMovieShows(theater);
				returnList.add(new HttpTheater(theater, showList));
			}		
		}
		return returnList;
	}
	
	/*private HttpTheater getTheater(long theaterId){
		Theater theater = theaterService.getTheater(theaterId);	
		return new HttpTheater(theater, true);
	}*/
	private TheaterImpl convert(HttpTheater httpTheater)
	{
		TheaterImpl theater = new TheaterImpl();
		theater.setTheaterId(httpTheater.theaterId);
		theater.setName(httpTheater.name);
		theater.setCity(httpTheater.city);
		theater.setState(httpTheater.state);
		theater.setZipcode(httpTheater.zipcode);
		return theater;
	}
}
