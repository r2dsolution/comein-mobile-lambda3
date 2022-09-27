package com.r2dsolution.comein.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedBooking {
	
	 	@JsonProperty("IsBooking") 
	    public boolean isBooking;
	 	
	    @JsonProperty("IsCancel") 
	    public boolean isCancel;
	    
	    @JsonProperty("TemplateLogic") 
	    public int templateLogic;
	    
	    @JsonProperty("FirstName") 
	    public String firstName;
	    
	    @JsonProperty("LastName") 
	    public String lastName;
	    
	    @JsonProperty("CheckInDate") 
	    public String checkInDate;
	    
	    @JsonProperty("CheckOutDate") 
	    public String checkOutDate;
	    
	    @JsonProperty("Email") 
	    public String email;
	    
	    @JsonProperty("ContactNo") 
	    public String contactNo;
	    
	    @JsonProperty("Nationality") 
	    public String nationality;
	    
	    @JsonProperty("RoomNight") 
	    public int roomNight;
	    
	    @JsonProperty("RoomType") 
	    public String roomType;
	    
	    @JsonProperty("BookingNumber") 
	    public String bookingNumber;
	    
	    @JsonProperty("Price") 
	    public double price;
	    
	    @JsonProperty("Adult") 
	    public int adult;
	    
	    @JsonProperty("Child") 
	    public Object child;
	    
	    @JsonProperty("HotelName") 
	    public String hotelName;
	    
	    @JsonProperty("DateReceive") 
	    public String dateReceive;
}
