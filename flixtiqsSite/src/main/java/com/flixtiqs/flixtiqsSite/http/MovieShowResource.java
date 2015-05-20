package com.flixtiqs.flixtiqsSite.http;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieShowImpl;
import com.flixtiqs.flixtiqsSite.entity.impl.TheaterImpl;
import com.flixtiqs.flixtiqsSite.http.entity.HttpShow;
import com.flixtiqs.flixtiqsSite.service.BrowsingService;
import com.flixtiqs.flixtiqsSite.service.MovieService;
import com.flixtiqs.flixtiqsSite.service.exception.FlixtiqsException;

@Path("/movieshow")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class MovieShowResource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BrowsingService theaterService;
	@Autowired
	private MovieService movieService;
	
	@Path("/")
	@POST
	public Response createMovieShow(HttpShow movieShow)
	{
		MovieShow show = convert(movieShow);
		Theater theater = addShowToTheater(movieShow, show);
		theaterService.update(theater);
		return Response.status(Status.CREATED).header("Location",  "/movieshow/").entity(new HttpShow(show, true)).build();
	}
	@GET
	@Path("/")
	@Wrapped(element ="movieshows")
	public List<HttpShow> getShowsSearch(@QueryParam("theater") Long theaterId, @QueryParam("movie") Long movieId, @QueryParam("time") String time) throws FlixtiqsException
	{
		List<MovieShow> foundList = new ArrayList<MovieShow>();
		if(theaterId != null && movieId != null)
		{
			foundList = theaterService.getMovieShow(movieService.getMovie(movieId), theaterService.getTheater(theaterId));
		}
		else if(theaterId != null)
		{
			foundList = theaterService.getPlayingMovieShows(theaterService.getTheater(theaterId));
		}
		List<HttpShow> returnList = new ArrayList<HttpShow>(foundList.size());
		for(MovieShow show : foundList)
		{
			returnList.add(new HttpShow(show, true));
		}
		return returnList;
 	}

	private MovieShow convert(HttpShow movieShow)
	{
		MovieShowImpl showImpl = new MovieShowImpl();
		showImpl.setPrice(movieShow.price);
		showImpl.setShowTime(movieShow.showTime);
		showImpl.setMovie(movieService.getMovie(movieShow.movieId));
		showImpl.setTheater(theaterService.getTheater(movieShow.theaterId));
		return showImpl;
	}
	private Theater addShowToTheater(HttpShow movieShow, MovieShow show)
	{
		TheaterImpl theater = new TheaterImpl();
		theater = (TheaterImpl)theaterService.getTheater(movieShow.theaterId);
		logger.info("getting theater" + theater.getTheaterId());
		theater.addPlayingMovies(show);
		return theater;
	}
	
}
