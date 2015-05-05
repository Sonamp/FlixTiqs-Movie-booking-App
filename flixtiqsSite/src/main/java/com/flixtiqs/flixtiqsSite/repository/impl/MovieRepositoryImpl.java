package com.flixtiqs.flixtiqsSite.repository.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flixtiqs.flixtiqsSite.entity.Movie;
import com.flixtiqs.flixtiqsSite.entity.impl.MovieImpl;
import com.flixtiqs.flixtiqsSite.repository.MovieRepository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * Add movie to database
	 */
	@Override
	public long addMovie(Movie movie) {
		return (Long)this.sessionFactory.getCurrentSession().save(movie);
		
	}
/**
 * get movies from database based on movie id
 */
	@Override
	public Movie getMovie(long movieId) {		
		return (Movie)this.sessionFactory.getCurrentSession().get(MovieImpl.class, movieId);
	}

	/**
	 * search movie by name
	 */
	@Override
	public Movie getMovie(String movieName) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class)
				.add(Restrictions.eq("name", movieName));	
		Object obj =  crit.uniqueResult();
		if(obj != null)
			return (Movie)obj;
		else
			return null;		
	}

	/**
	 * search movie which are released after input date
	 */
	@Override
	public List<Movie> getMovieAfterReleasedDate(Date date) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class)
				.add(Restrictions.gt("releasedDate", date));		
		return crit.list();
	}

	/**
	 * return all movies
	 */
	@Override
	public List<Movie> getAllMovie() {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class);
		return crit.list();
	}

	/**
	 * update movie to the database
	 */
	@Override
	public void update(Movie movie) {
		this.sessionFactory.getCurrentSession().update(movie);
		
	}

}
