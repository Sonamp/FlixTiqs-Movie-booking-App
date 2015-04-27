package com.flixtiqs.flixtiqsSite.entity.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.flixtiqs.flixtiqsSite.entity.MovieShow;

/**
 * Implementation of MovieShow interface extending movie implementation class
 * @author sonam
 *
 */
@Component
public class MovieShowImpl extends MovieImpl implements MovieShow {

	private double price;
	private String[] showTime;
	
	//constructor will call super class constructor
	MovieShowImpl()
	{
		super();
		this.price =  10.0;
		this.showTime = new String[]{"10:30AM", "2.15PM", "4.00PM", "8.00PM" };
	}
	
	public double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}

	public String[] getShowTime() {
		// TODO Auto-generated method stub
		return this.showTime;
	}
	public void setShowTime(String[] shows)
	{
		this.showTime = shows;
	}

}
