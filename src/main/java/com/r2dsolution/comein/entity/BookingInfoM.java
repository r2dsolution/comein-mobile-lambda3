package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("booking_info")
public class BookingInfoM implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String bookingNo;
	private java.sql.Date bookingDate;
	
	
	
	//private String email;
	private String ownerId;
	private String roomName;
	private String roomDesc;
	
	private java.sql.Date checkin;
	private java.sql.Date checkout;
	
	private String refName;
	private long visitorAdult;
    private Long visitorChild;
	


	AggregateReference<HotelM, Long> hotelId;
	
//	@MappedCollection(idColumn="booking_id", keyColumn="ref_id")
//	Map<String,BookingKYCInfoM> kycInfo = new HashMap<String,BookingKYCInfoM>();
//	
//	public void addBookingKYC(BookingKYCInfoM bookKYC) {
//	//	bookKYC.setBookingId( AggregateReference.to(this.id));
//		kycInfo.put(bookKYC.getRefId(), bookKYC);
//	}
//	
//	public void removeBookingKYC(BookingKYCInfoM bookKYC) {
//		this.getKycInfo().remove(bookKYC.getRefId());
//	}
//	public void removeBookingKYC(String refId) {
//		this.getKycInfo().remove(refId);
//	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}



	
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

	

	public AggregateReference<HotelM, Long> getHotelId() {
		return hotelId;
	}

	public void setHotelId(AggregateReference<HotelM, Long> hotelId) {
		this.hotelId = hotelId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	

	public java.sql.Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(java.sql.Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public java.sql.Date getCheckin() {
		return checkin;
	}

	public void setCheckin(java.sql.Date checkin) {
		this.checkin = checkin;
	}

	public java.sql.Date getCheckout() {
		return checkout;
	}

	public void setCheckout(java.sql.Date checkout) {
		this.checkout = checkout;
	}

	

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public long getVisitorAdult() {
		return visitorAdult;
	}

	public void setVisitorAdult(long visitorAdult) {
		this.visitorAdult = visitorAdult;
	}

	public Long getVisitorChild() {
		return visitorChild;
	}

	public void setVisitorChild(Long visitorChild) {
		this.visitorChild = visitorChild;
	}

	
	
	
	

}
