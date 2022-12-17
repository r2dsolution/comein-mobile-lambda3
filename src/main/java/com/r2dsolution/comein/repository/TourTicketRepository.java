package com.r2dsolution.comein.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.r2dsolution.comein.entity.TourTicketM;

public interface TourTicketRepository extends CrudRepository<TourTicketM, Long> {
	

	@Modifying
	@Query(name="query.BookingTicket")
	public int updateBookingTicket(@Param("booking_code") String bookingCode,@Param("status") String status,@Param("updated_user") String userId,@Param("tour_id") Long tourId, @Param("tour_date") java.sql.Date tourDate);

}
