package com.flixtiqs.flixtiqsSite;



import java.sql.Date;
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
import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieImpl;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieShowImpl;
import com.flixtiqs.flixtiqsSite.entity.impl.TheaterImpl;
import com.flixtiqs.flixtiqsSite.service.BrowsingService;
import com.flixtiqs.flixtiqsSite.service.MovieService;
/**
 * Test class to test browsing service to test its methods
 * @author sonam
 *
 */
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestBrowsingService  extends AbstractJUnit4SpringContextTests {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BrowsingService theaterService;
	
	@Autowired
	private MovieService movieService;
	/**
	 * TestGetTheaterByName assert the theater search by name and print theater location and the playing movies with their show timings
	 */
	@Test
	public void TestAddandGetTheater()
	{

		TheaterImpl theater = new TheaterImpl();
		theater.setName("Theater_" + new Random().nextInt(200));
		theater.setCity("City_" + new Random().nextInt(200) );
		theater.setState("ST_"+ new Random().nextInt(200));
		theater.setZipcode(String.valueOf((new Random().nextInt(9999)+ 80000)));
		Theater added = theaterService.addTheater(theater);
		
		logger.info("Theater added "+ added);
		Assert.assertNotEquals(0, added.getTheaterId());
		Assert.assertEquals(theater.getName(), added.getName());
		Assert.assertEquals(theater.getCity(), added.getCity());
		Assert.assertEquals(theater.getState(), added.getState());
		Assert.assertEquals(theater.getZipcode(), added.getZipcode());
		
		Theater found = theaterService.getTheater(added.getTheaterId());
		Assert.assertEquals(found.getName(), added.getName());
		Assert.assertEquals(found.getCity(), added.getCity());
		Assert.assertEquals(found.getState(), added.getState());
		Assert.assertEquals(found.getZipcode(), added.getZipcode());
	}
	@Test
	public void TestGetTheaterByName()
	{
		List<Theater> theaterList = theaterService.getTheaterByName("AMC Marcado");
		Assert.assertEquals(1, theaterList.size());
	}
	@Test
	public void TestGetNoTheaterByName()
	{
		List<Theater> theaterList =  theaterService.getTheaterByName("CineMax");
		Assert.assertEquals(0, theaterList.size());
	}
	@Test
	public void TestGetTheaterByCityState()
	{
		List<Theater> theaterList = theaterService.getTheaterByCityState("Santa Clara", "CA");
		Assert.assertEquals(1, theaterList.size());
	}
	@Test
	public void TestGetNoTheaterByCityState()
	{
		List<Theater> theaterList = theaterService.getTheaterByCityState("Seattle", "WA");
		Assert.assertEquals(0, theaterList.size());
	}
	@Test
	public void TestGetTheaterByZipcode()
	{
		List<Theater> theaterList = theaterService.getTheaterByZipcode("95054");
		Assert.assertEquals(1, theaterList.size());
	}
	@Test
	public void TestGetNoTheaterByZipcode()
	{
		List<Theater> theaterList = theaterService.getTheaterByZipcode("95132");
		Assert.assertEquals(0, theaterList.size());
	}
	
	/**
	 * Test to add and get the movie shows to the theater 
	 */
	@Test
	public void TestAddMovieInTheater()
	{
		Movie movie1 = createMovie();
		Movie movie2 =  createMovie();
		
		Theater theater1 = createTheater();
		Theater theater2 =  createTheater();
		
		MovieShowImpl movie1Show1= new MovieShowImpl();
		movie1Show1.setPrice(10.0);
		movie1Show1.setShowTime("10:00");
		movie1Show1.setMovie(movie1);
		movie1Show1.setTheater(theater1);
		theater1.addPlayingMovies(movie1Show1);
		
		MovieShowImpl movie1Show2= new MovieShowImpl();
		movie1Show2.setPrice(10.0);
		movie1Show2.setShowTime("15:30");
		movie1Show2.setMovie(movie1);
		movie1Show2.setTheater(theater1);
		theater1.addPlayingMovies(movie1Show2);
		
		MovieShowImpl movie2Show1= new MovieShowImpl();
		movie2Show1.setPrice(10.0);
		movie2Show1.setShowTime("11:30");
		movie2Show1.setMovie(movie2);
		movie2Show1.setTheater(theater1);
		theater1.addPlayingMovies(movie2Show1);
		
		MovieShowImpl movie2Show2= new MovieShowImpl();
		movie2Show2.setPrice(10.0);
		movie2Show2.setShowTime("13:00");
		movie2Show2.setMovie(movie2);
		movie2Show2.setTheater(theater2);
		theater2.addPlayingMovies(movie2Show2);
		
		MovieShowImpl movie1Show3= new MovieShowImpl();
		movie1Show3.setPrice(10.0);
		movie1Show3.setShowTime("16:15");
		movie1Show3.setMovie(movie1);
		movie1Show3.setTheater(theater2);
		theater2.addPlayingMovies(movie1Show3);
		
		theaterService.update(theater1);
		theaterService.update(theater2);
				
		Theater foundTheater1 =  theaterService.getTheater(theater1.getTheaterId());
		Theater foundTheater2 = theaterService.getTheater(theater2.getTheaterId());
		
		Movie fndMovie1 = movieService.getMovie(movie1.getMovieId());
		Movie fndMovie2 = movieService.getMovie(movie2.getMovieId());
		//Assert playing movies in theater1
		Assert.assertEquals(2, theaterService.getPlayingMovies(foundTheater1).size());
		//Assert movie shows of movie1 in theater1
		Assert.assertEquals(2, theaterService.getMovieShow(fndMovie1, foundTheater1).size());
		//Assert playing movie shows in theater2
		Assert.assertEquals(2, theaterService.getPlayingMovieShows(foundTheater2).size());
		//Assert the theaters which are playing movie2
		Assert.assertEquals(2, theaterService.getTheaterForMovie(fndMovie2).size());
	}
	
	private Movie createMovie()
	{
		MovieImpl movie = new MovieImpl();
		movie.setName("Test"+new Random().nextInt(200));
		movie.setLength(150);
		movie.setreleasedDate(Date.valueOf("2015-05-12"));
		movie.setRating(3.5);
		return movieService.addMovie(movie);		
	}
	private Theater createTheater()
	{
		TheaterImpl theater = new TheaterImpl();
		theater.setName("Theater_" + new Random().nextInt(200));
		theater.setCity("City_" + new Random().nextInt(200) );
		theater.setState("ST_"+ new Random().nextInt(200));
		theater.setZipcode(String.valueOf((new Random().nextInt(9999)+ 80000)));
		return theaterService.addTheater(theater);
	}
	
}
