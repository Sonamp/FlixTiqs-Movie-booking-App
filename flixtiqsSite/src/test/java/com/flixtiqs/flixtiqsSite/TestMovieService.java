package com.flixtiqs.flixtiqsSite;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieImpl;
import com.flixtiqs.flixtiqsSite.service.MovieService;

/**
 * Test class to test MovieService
 * @author sonam
 *
 */
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestMovieService extends AbstractJUnit4SpringContextTests {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MovieService movieService;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	@Test
	public void TestAddGetMovie()
	{
		List<Movie> movieList = movieService.getMovieAfterReleasedDate(Date.valueOf("2015-05-01"));
		int count = movieList.size();
		
		List<Movie> allMovieList = movieService.getAllMovie();
		int allCnt = allMovieList.size();
		
		MovieImpl movie = new MovieImpl();
		movie.setName("Test"+new Random().nextInt(200));
		movie.setLength(140);
		movie.setreleasedDate(Date.valueOf("2015-05-10"));
		movie.setRating(4.0);
		Movie added = movieService.addMovie(movie);
		logger.info("movie added "+ added);
		Assert.assertNotEquals(0, added.getMovieId());
		Assert.assertEquals(movie.getName(), added.getName());
		Assert.assertEquals(movie.getLength(), added.getLength());
		Assert.assertEquals(movie.getReleasedDate(), added.getReleasedDate());
		Assert.assertEquals(movie.getRating(), added.getRating(), 0.0);
		
		Movie found = movieService.getMovie(added.getMovieId());
		Assert.assertEquals(found.getMovieId(), added.getMovieId());
		Assert.assertEquals(found.getName(), added.getName());
		Assert.assertEquals(found.getLength(), added.getLength());
		Assert.assertEquals(df.format(found.getReleasedDate()), df.format(added.getReleasedDate()));
		Assert.assertEquals(found.getRating(), added.getRating(), 0.0);
		
		//count after adding movie
		count++;
		allCnt++;
		
		movieList = movieService.getMovieAfterReleasedDate(Date.valueOf("2015-05-01"));
		Assert.assertEquals(count, movieList.size());
		
		allMovieList = movieService.getAllMovie();
		Assert.assertEquals(allCnt, allMovieList.size());
	}
	@Test
	public void TestGetMovie()
	{
		Movie movie = movieService.getMovie("Bombay Velvet");
		Assert.assertEquals("Bombay Velvet", movie.getName());
		//Assert.assertEquals(4.0, movie.getRating());
	}
	@Test
	public void TestGetNoMovie()
	{
		Movie movie = movieService.getMovie("NH10");
		Assert.assertNull("No movie found", movie);
	}
	
	@Test
	public void TestGetNoMovieAfterReleasedDate()
	{
		List<Movie> movieList = movieService.getMovieAfterReleasedDate(Date.valueOf("2015-07-10"));
		Assert.assertEquals(0, movieList.size());
	}
	
	
	 
}
