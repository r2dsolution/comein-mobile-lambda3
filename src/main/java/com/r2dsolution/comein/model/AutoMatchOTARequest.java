package com.r2dsolution.comein.model;

import java.io.Serializable;

public class AutoMatchOTARequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private String hotelName;
	private int hotelId;
	private boolean isCancel = false;
	
	public boolean isCancel() {
		return isCancel;
	}
	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}
//	public String getHotelName() {
//		return hotelName;
//	}
//	public void setHotelName(String hotelName) {
//		this.hotelName = hotelName;
//	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

}
