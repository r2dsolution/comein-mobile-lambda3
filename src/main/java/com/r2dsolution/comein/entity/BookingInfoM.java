package com.r2dsolution.comein.entity;

import java.io.Serializable;
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
	
	private String refName;
	private String refName2;
//	
//	private String customerFullname;
//	private String customerEmail;
	private String targetId;
	
	private Date checkin;
	private Date checkout;
	
	//private String email;
	private String ownerId;
	private String roomName;
	private String roomDesc;
	private int visitorAdult;
	private int visitorChild;
	
	AggregateReference<HotelM, Long> hotelId;
	
	@MappedCollection(idColumn="booking_id", keyColumn="ref_id")
	Map<String,BookingKYCInfoM> kycInfo = new HashMap<String,BookingKYCInfoM>();
	
	public void addBookingKYC(BookingKYCInfoM bookKYC) {
	//	bookKYC.setBookingId( AggregateReference.to(this.id));
		kycInfo.put(bookKYC.getRefId(), bookKYC);
	}
	
	public void removeBookingKYC(BookingKYCInfoM bookKYC) {
		this.getKycInfo().remove(bookKYC.getRefId());
	}
	public void removeBookingKYC(String refId) {
		this.getKycInfo().remove(refId);
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

//	public String getCustomerName() {
//		return customerName;
//	}
//
//	public void setCustomerName(String customerName) {
//		this.customerName = customerName;
//	}

	
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

//	public String getCustomerFullname() {
//		return customerFullname;
//	}
//
//	public void setCustomerFullname(String customerFullname) {
//		this.customerFullname = customerFullname;
//	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
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

	public int getVisitorAdult() {
		return visitorAdult;
	}

	public void setVisitorAdult(int visitorAdult) {
		this.visitorAdult = visitorAdult;
	}

	public int getVisitorChild() {
		return visitorChild;
	}

	public void setVisitorChild(int visitorChild) {
		this.visitorChild = visitorChild;
	}

//	public String getCustomerEmail() {
//		return customerEmail;
//	}
//
//	public void setCustomerEmail(String customerEmail) {
//		this.customerEmail = customerEmail;
//	}
	
	

	public Map<String, BookingKYCInfoM> getKycInfo() {
		return kycInfo;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public void setKycInfo(Map<String, BookingKYCInfoM> kycInfo) {
		this.kycInfo = kycInfo;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getRefName2() {
		return refName2;
	}

	public void setRefName2(String refName2) {
		this.refName2 = refName2;
	}

	

	
	
	

}
