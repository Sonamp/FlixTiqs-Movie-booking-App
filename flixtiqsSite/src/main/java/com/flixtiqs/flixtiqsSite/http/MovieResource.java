package com.flixtiqs.flixtiqsSite.http;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.flixtiqs.flixtiqsSite.entity.impl.MovieImpl;
import com.flixtiqs.flixtiqsSite.http.entity.HttpMovie;
import com.flixtiqs.flixtiqsSite.service.MovieService;
import com.flixtiqs.flixtiqsSite.service.exception.ErrorCode;
import com.flixtiqs.flixtiqsSite.service.exception.FlixtiqsException;

@Path("/movies")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class MovieResource {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MovieService movieService;
	
	@POST
	@Path("/")
	public Response createMovie(HttpMovie newMovie){
		Movie movie = convert(newMovie);
		Movie addedMovie = movieService.addMovie(movie);
		return Response.status(Status.CREATED).header("Location", "/movies/"+addedMovie.getMovieId()).entity(new HttpMovie(addedMovie)).build();
	}	

	@GET
	@Path("/{movieId}")	
	public HttpMovie getMovieById(@PathParam("movieId") long movieId){
		logger.info("getting movie by id:"+movieId);
		Movie movie = movieService.getMovie(movieId);	
		return new HttpMovie(movie);
	}
	
	
	@GET
	@Path("/")
	@Wrapped(element="movies")
	public List<HttpMovie> getMovieSearch(@QueryParam("name") String name, @QueryParam("date") String releasedDate) throws FlixtiqsException {
		List<Movie> foundList = new ArrayList<Movie>();
		if(name !=null && !name.isEmpty())
		{
			logger.info("getting movie by name:"+name);
			foundList = movieService.getMovie(name);
		}
		else if(releasedDate != null && !releasedDate.isEmpty())
		{
			logger.info("getting movie by released date:"+releasedDate);
			foundList = movieService.getMovieAfterReleasedDate(Date.valueOf(releasedDate));			
		}
		else
		{
			logger.info("getting all movies");
			foundList = movieService.getAllMovie();
		}
		List<HttpMovie> returnList = new ArrayList<>(foundList.size());
		for(Movie movie : foundList){
			returnList.add(new HttpMovie(movie));
		}
		return returnList;
	}
	
	@PUT
	@Path("/")
	public Response updateMovie(HttpMovie upMovie)
	{
		Movie movie = convert(upMovie);
		movieService.update(movie);
		logger.info("updating movie :"+movie.getMovieId());
		return Response.status(Status.CREATED).header("Location", "/movies/"+movie.getMovieId()).entity(new HttpMovie(movieService.getMovie(movie.getMovieId()))).build();
	}
	@DELETE
	@Path("/")
	public Response deleteMovie(HttpMovie dlMovie)
	{
		Movie movie = convert(dlMovie);
		logger.info("Deleting movie :"+movie.getMovieId());
		movieService.delete(movie);		
		return Response.status(Status.CREATED).header("Location", "/movies/").build();
	}
	
	private Movie convert(HttpMovie httpMovie)
	{
		MovieImpl movieImpl = new MovieImpl();
		try
		{
		movieImpl.setLength(httpMovie.length);
		movieImpl.setMovieId( httpMovie.movieId);
		movieImpl.setName(httpMovie.name);
		movieImpl.setRating(httpMovie.rating);
		movieImpl.setreleasedDate(Date.valueOf(httpMovie.releasedDate));
		movieImpl.setCategory(httpMovie.category);
		movieImpl.setDeleted(httpMovie.isdeleted);
		}
		catch(Exception e)
		{
			throw new FlixtiqsException(ErrorCode.MISSING_DATA, "Data is missing");
		}
		return movieImpl;
	}
}
