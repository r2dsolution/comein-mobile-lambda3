package com.r2dsolution.comein.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("booking_kyc")
public class BookingKYCInfoM  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private Long userKycId;
	
//	private String firstname;
//	private String lastname;
//	private String fullname;
//	private String email;
	private String refId;
	private String refType;
	
	//AggregateReference<BookingInfoM, Long> bookingId;
	
	
	
	public BookingKYCInfoM() {
		super();
		
	}
	public BookingKYCInfoM(UserKYCInfoM user) {
		super();
		this.userKycId = user.getId();
//		this.firstname = user.getFirstname();
//		this.lastname = user.getLastname();
//		this.fullname = user.getFirstname()+" "+user.getLastname();
//		this.email = user.getEmail();
		this.refId = user.getRefId();
		this.refType = user.getRefType();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public String getFirstname() {
//		return firstname;
//	}
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//	public String getLastname() {
//		return lastname;
//	}
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//	public String getFullname() {
//		return fullname;
//	}
//	public void setFullname(String fullname) {
//		this.fullname = fullname;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
//	public AggregateReference<BookingInfoM, Long> getBookingId() {
//		return bookingId;
//	}
//	public void setBookingId(AggregateReference<BookingInfoM, Long> bookingId) {
//		this.bookingId = bookingId;
//	}
	
	public Long getUserKycId() {
		return userKycId;
	}
	public void setUserKycId(Long userKycId) {
		this.userKycId = userKycId;
	}
	
	

}
