package com.r2dsolution.comein.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.r2dsolution.comein.entity.Booking;


public interface BookingRepository  extends CrudRepository<Booking, Long> {
	public List<Booking> findByOwnerId(String comeinId) throws Exception;

}
