package com.r2dsolution.comein.entity.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("v_topup")
public class TopUpRateView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String topupId;
	
	private String companyId;
	
	private String companyName;
	
	private Date updatedDate;
	
	private BigDecimal minPeriod;
	
	private BigDecimal maxPeriod;
	
	private BigDecimal topupRate;
	
	private BigDecimal comeinRate;
	
	private BigDecimal hotelRate;
	
	private boolean useDefault;



	public String getTopupId() {
		return topupId;
	}

	public void setTopupId(String topupId) {
		this.topupId = topupId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getMinPeriod() {
		return minPeriod;
	}

	public void setMinPeriod(BigDecimal minPeriod) {
		this.minPeriod = minPeriod;
	}

	public BigDecimal getMaxPeriod() {
		return maxPeriod;
	}

	public void setMaxPeriod(BigDecimal maxPeriod) {
		this.maxPeriod = maxPeriod;
	}

	public BigDecimal getTopupRate() {
		return topupRate;
	}

	public void setTopupRate(BigDecimal topupRate) {
		this.topupRate = topupRate;
	}

	public BigDecimal getComeinRate() {
		return comeinRate;
	}

	public void setComeinRate(BigDecimal comeinRate) {
		this.comeinRate = comeinRate;
	}

	public BigDecimal getHotelRate() {
		return hotelRate;
	}

	public void setHotelRate(BigDecimal hotelRate) {
		this.hotelRate = hotelRate;
	}

	public boolean isUseDefault() {
		return useDefault;
	}

	public void setUseDefault(boolean useDefault) {
		this.useDefault = useDefault;
	}
	
	
	

}
