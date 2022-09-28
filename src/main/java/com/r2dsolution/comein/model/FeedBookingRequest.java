package com.r2dsolution.comein.model;

import java.io.Serializable;

import com.r2dsolution.comein.api.model.FeedBooking;

public class FeedBookingRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String date;
	private FeedBooking booking;
	
	
	public FeedBookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeedBookingRequest(String date, FeedBooking json) {
		this.date=date;
		this.booking = json;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public FeedBooking getBooking() {
		return booking;
	}
	public void setBooking(FeedBooking booking) {
		this.booking = booking;
	}
}
