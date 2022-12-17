package com.r2dsolution.comein.entity.view;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("v_payment_condition")
public class PaymentConditionView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String conditionId;
	
	private Long companyId;
	
	private String companyName;
	
	private int payableDay;
	
	private int payableTourDay;
	
	private boolean useDefault;

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getPayableDay() {
		return payableDay;
	}

	public void setPayableDay(int payableDay) {
		this.payableDay = payableDay;
	}

	public int getPayableTourDay() {
		return payableTourDay;
	}

	public void setPayableTourDay(int payableTourDay) {
		this.payableTourDay = payableTourDay;
	}

	public boolean isUseDefault() {
		return useDefault;
	}

	public void setUseDefault(boolean useDefault) {
		this.useDefault = useDefault;
	}
	
	
	

}
