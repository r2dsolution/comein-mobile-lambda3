package com.r2dsolution.comein.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.r2dsolution.comein.entity.OTABookingM;

public interface OTABookingRepository  extends CrudRepository<OTABookingM, Long> {

	public Optional<OTABookingM> findByBookingNumber(String number);
	public List<OTABookingM> findByHotelName(String hotel);
}
