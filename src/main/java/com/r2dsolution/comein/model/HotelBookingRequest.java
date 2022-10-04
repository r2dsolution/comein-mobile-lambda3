package com.r2dsolution.comein.model;

import java.io.Serializable;

public class HotelBookingRequest implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long otaBookingId;
	private String bookingNumber;
	private Long hotelId;
	private boolean isCancel;
	private String otaStatus;

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public Long getOtaBookingId() {
		return otaBookingId;
	}

	public void setOtaBookingId(Long otaBookingId) {
		this.otaBookingId = otaBookingId;
	}
	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getOtaStatus() {
		return otaStatus;
	}

	public void setOtaStatus(String otaStatus) {
		this.otaStatus = otaStatus;
	}

	
	
	
}
