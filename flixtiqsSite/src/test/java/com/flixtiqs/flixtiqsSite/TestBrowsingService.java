package com.flixtiqs.flixtiqsSite;



import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
import com.flixtiqs.flixtiqsSite.service.BrowsingService;
/**
 * Test class to test browsing service
 * @author sonam
 *
 */
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestBrowsingService  extends AbstractJUnit4SpringContextTests {
	@Autowired
	private BrowsingService theaterService;
	/**
	 * TestGetTheaterByName assert the theater search by name and print theater location and the playing movies with their show timings
	 */
	@Test
	public void TestGetTheaterByName()
	{
		Theater theater = theaterService.getTheaterByName("AMC Marcado");
		Assert.assertEquals("AMC Marcado", theater.getName());
		System.out.println(theater.getName() + " " + theater.getCity()  + " " + theater.getState()  + " " + theater.getZipcode());
		System.out.println("Playing movies");
		for (MovieShow show : theater.getPlayingMovies())
		{
			System.out.println("Name: "+ show.getName() + ", Length:" + show.getLength() + "Min, Rating:" + show.getRating() + ", Ticket Price:" + show.getPrice() + "$");
			System.out.print("Show Timings: ");
			for(String time : show.getShowTime())
			{
				System.out.print(time + ", ");
			}			
		}
	}
	@Test
	public void TestGetNoTheaterByName()
	{
		Theater theater = theaterService.getTheaterByName("CineMax");
		Assert.assertNull("No Theater found", theater);
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
}
