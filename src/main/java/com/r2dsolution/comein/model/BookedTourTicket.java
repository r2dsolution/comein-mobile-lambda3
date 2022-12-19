package com.r2dsolution.comein.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookedTourTicket extends TourTicket{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ownerId;
	private String referenceName;
	private String bookingStatus;
	private int adult;
	private int child;
	
	@JsonProperty("owner-id")
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@JsonProperty("reference-name")
	public String getReferenceName() {
		return referenceName;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	@JsonProperty("booking-status")
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public int getAdult() {
		return adult;
	}
	public void setAdult(int adult) {
		this.adult = adult;
	}
	public int getChild() {
		return child;
	}
	public void setChild(int child) {
		this.child = child;
	}
	
	

}
