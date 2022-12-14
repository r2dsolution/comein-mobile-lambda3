package com.r2dsolution.comein.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.r2dsolution.comein.entity.OTABookingM;

public interface OTABookingRepository  extends CrudRepository<OTABookingM, Long> {

	public List<OTABookingM> findByBookingNumber(String number);
	public Optional<OTABookingM> findByMessageId(String messageId);
	public List<OTABookingM> findByHotelName(String hotel);
	public List<OTABookingM> findByHotelNameAndStatusAndIsBookingIsTrueAndIsCancelIsFalse(String hotel,String status);
	public List<OTABookingM> findByHotelNameAndStatusAndIsCancelAndIsBookingIsTrue(String hotel,String status,boolean isCancel);
}
