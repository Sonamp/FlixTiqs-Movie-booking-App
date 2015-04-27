package com.flixtiqs.flixtiqsSite;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.service.MovieService;

/**
 * Test class to test MovieService
 * @author sonam
 *
 */
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestMovieService extends AbstractJUnit4SpringContextTests {
	@Autowired
	private MovieService movieService;
	@Test
	public void TestGetMovie()
	{
		Movie movie = movieService.getMovie("AvengerReturn");
		Assert.assertEquals("AvengerReturn", movie.getName());
		//Assert.assertEquals(4.0, movie.getRating());
	}
	@Test
	public void TestGetNoMovie()
	{
		Movie movie = movieService.getMovie("Avenger");
		Assert.assertNull("No movie found", movie);
	}
	@Test
	public void TestGetMovieAfterReleasedDate()
	{
		List<Movie> movieList = movieService.getMovieAfterReleasedDate(new Date());
		Assert.assertEquals(1, movieList.size());
	}
	@Test
	public void TestGetNoMovieAfterReleasedDate()
	{
		List<Movie> movieList = movieService.getMovieAfterReleasedDate(new Date(2015,06, 01));
		Assert.assertEquals(0, movieList.size());
	}
	@Test
	public void TestGetAllMovie()
	{
		List<Movie> movieList = movieService.getAllMovie();
		Assert.assertEquals(1, movieList.size());
	}
}
