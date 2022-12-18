package com.r2dsolution.comein.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tour_ticket")
public class TourTicketM {

	@Id
	private Long id;
	
	private BigDecimal adultRate;
	
	private BigDecimal childRate;
	
	private String bookingCode;
	
	private String cancelable;
	
	private int cancelBefore;
	
	
}
