package com.r2dsolution.comein.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ota_booking")
public class OTABookingM implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private boolean isBooking;
	
	private boolean isCancel;
	
	private int templateLogic;
	
	private String firstName;
	
	private String lastName;
	
	 private java.sql.Date checkinDate;
	 
	 private java.sql.Date checkoutDate;
	 
	 private String email;
	 
	 private String contactNo;
	 
	 private String nationality;
	 
	 private int roomNight;
	 
	 private String roomType;
	 
	 private String bookingNumber;
	 
	 private double price;
	 
	 private int adult;
	 
	 private int child;
	 
	 private String hotelName;
	 
	 private java.sql.Timestamp dateReceive;
	 private java.sql.Timestamp createdDate;
	 private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isBooking() {
		return isBooking;
	}

	public void setBooking(boolean isBooking) {
		this.isBooking = isBooking;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public int getTemplateLogic() {
		return templateLogic;
	}

	public void setTemplateLogic(int templateLogic) {
		this.templateLogic = templateLogic;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	

	public java.sql.Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(java.sql.Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public java.sql.Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(java.sql.Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getRoomNight() {
		return roomNight;
	}

	public void setRoomNight(int roomNight) {
		this.roomNight = roomNight;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public java.sql.Timestamp getDateReceive() {
		return dateReceive;
	}

	public void setDateReceive(java.sql.Timestamp dateReceive) {
		this.dateReceive = dateReceive;
	}

	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.sql.Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	 

}
