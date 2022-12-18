package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tour_booking")
public class TourBookingM implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private Long companyId;
	
	private Long tourId;
	
	private java.sql.Date tourDate;
	
	private String bookingCode;
	
	private String ownerId;
	
	private String referenceName;
	
	private String locationPickup;
	
	private java.sql.Date bookingDate;
	
	private Date expireDate;
	
	private String paymentMethod;
	
	private int totalAdult;
	
	private int totalChild;
	
	private BigDecimal ticketSellValue;
	
	private BigDecimal hotelSellValue;
	
	private BigDecimal comeinSellValue;
	
	private BigDecimal netValue;
	
	private BigDecimal adultRate;
	
	private BigDecimal childRate;
	
	private String remark;
	
	private String status;
	
	private String hotels;
	
	private Date createdDate;
	
	private String createdBy;
	
	private Date updatedDate;
	
	private String updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}

	public java.sql.Date getTourDate() {
		return tourDate;
	}

	public void setTourDate(java.sql.Date tourDate) {
		this.tourDate = tourDate;
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getLocationPickup() {
		return locationPickup;
	}

	public void setLocationPickup(String locationPickup) {
		this.locationPickup = locationPickup;
	}

	public java.sql.Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(java.sql.Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getTotalAdult() {
		return totalAdult;
	}

	public void setTotalAdult(int totalAdult) {
		this.totalAdult = totalAdult;
	}

	public int getTotalChild() {
		return totalChild;
	}

	public void setTotalChild(int totalChild) {
		this.totalChild = totalChild;
	}

	public BigDecimal getTicketSellValue() {
		return ticketSellValue;
	}

	public void setTicketSellValue(BigDecimal ticketSellValue) {
		this.ticketSellValue = ticketSellValue;
	}

	public BigDecimal getHotelSellValue() {
		return hotelSellValue;
	}

	public void setHotelSellValue(BigDecimal hotelSellValue) {
		this.hotelSellValue = hotelSellValue;
	}

	public BigDecimal getComeinSellValue() {
		return comeinSellValue;
	}

	public void setComeinSellValue(BigDecimal comeinSellValue) {
		this.comeinSellValue = comeinSellValue;
	}

	public BigDecimal getNetValue() {
		return netValue;
	}

	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHotels() {
		return hotels;
	}

	public void setHotels(String hotels) {
		this.hotels = hotels;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public BigDecimal getAdultRate() {
		return adultRate;
	}

	public void setAdultRate(BigDecimal adultRate) {
		this.adultRate = adultRate;
	}

	public BigDecimal getChildRate() {
		return childRate;
	}

	public void setChildRate(BigDecimal childRate) {
		this.childRate = childRate;
	}
	

}
