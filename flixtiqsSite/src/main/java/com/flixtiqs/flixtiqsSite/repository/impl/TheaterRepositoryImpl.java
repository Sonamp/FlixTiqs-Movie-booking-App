package com.flixtiqs.flixtiqsSite.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.MovieShow;
import com.flixtiqs.flixtiqsSite.entity.Theater;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieShowImpl;
import com.flixtiqs.flixtiqsSite.entity.impl.TheaterImpl;
import com.flixtiqs.flixtiqsSite.repository.TheaterRepository;
@Repository
public class TheaterRepositoryImpl implements TheaterRepository {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * Add theater to the database
	 */
	@Override
	public long addTheater(Theater theater) {		
		return (Long)this.sessionFactory.getCurrentSession().save(theater);
	}

	/**
	 * search theater based on theater id
	 */
	@Override
	public Theater getTheater(long id) {
		return (Theater)this.sessionFactory.getCurrentSession().get(TheaterImpl.class, id);
	}

	/**
	 * search theater by name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Theater> getTheater(String theaterName) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheaterImpl.class)
				.add(Restrictions.ilike("name", "%"+theaterName+"%"));
		return crit.list();
	}
	/**
	 * search theater by location city+state
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Theater> getTheater(String city, String state) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheaterImpl.class)
				.add(Restrictions.ilike("city", "%"+city+"%")).add(Restrictions.eq("state", state));
		return crit.list();
	}
	/**
	 * search theater by zipcode
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Theater> getTheaterByZip(String zipcode) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheaterImpl.class)
				.add(Restrictions.ilike("zipcode", "%"+zipcode+"%"));
		return crit.list();
	}

	/**
	 * update theater to the database
	 */
	@Override
	public void update(Theater theater) {
			this.sessionFactory.getCurrentSession().update(theater);	
	}

	/**
	 * get movie shows in a theater for given movie
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieShow> getMovieShow(Movie movie, Theater theater) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieShowImpl.class)
				.add(Restrictions.eq("theater", theater)).add(Restrictions.eq("movie", movie));
		return crit.list();
	}
	/**
	 * search all the theater who are playing the searched movie
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Theater> getTheaterForMovie(Movie movie) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieShowImpl.class)
				.add(Restrictions.eq("movie", movie));
		//		.add(Restrictions.eq("movie", movie)).setResultTransformer(DistinctResultTransformer.INSTANCE);		
		List<MovieShow> list = crit.list();
		List<Theater> theaters = new ArrayList<Theater>();
		for(MovieShow show : list)
		{
			if(!theaters.contains(show.getTheater()))
				theaters.add(show.getTheater());
		}
		return theaters;
	}
	/**
	 * get all movies shows in the searched theater
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieShow> getPlayingMovieShows(Theater theater) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieShowImpl.class)
				.add(Restrictions.eq("theater", theater));
		return crit.list();
	}
	/**
	 * get only movies in the searched theater
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getPlayingMovies(Theater theater) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieShowImpl.class)
				.add(Restrictions.eq("theater", theater));
		List<MovieShow> list = crit.list();
		List<Movie> movies = new ArrayList<Movie>();
		for(MovieShow show : list)
		{
			if(!movies.contains(show.getMovie()))
				movies.add(show.getMovie());
		}
		return movies;
	}

	

}