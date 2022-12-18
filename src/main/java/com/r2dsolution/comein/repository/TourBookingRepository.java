package com.r2dsolution.comein.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.r2dsolution.comein.entity.TourBookingM;
import com.r2dsolution.comein.entity.TourTicketM;


public interface TourBookingRepository extends CrudRepository<TourBookingM, Long> {

	@Modifying
	@Query(name="query.InsertBooking")
	public int addTourBooking(@Param("param_code") String code,@Param("param_owner") String owner,@Param("param_ref") String ref
			,@Param("param_location") String location,@Param("param_adult") int adult,@Param("param_child") int child
			,@Param("param_sell_value") BigDecimal sellValue,@Param("param_hotel_value") BigDecimal hotelValue,@Param("param_comein_value") BigDecimal comeinValue,@Param("param_net_value") BigDecimal netValue
			,@Param("param_remark") String remark,@Param("param_status") String status
			,@Param("param_tour") Long tourId, @Param("param_hotels") String hotels,@Param("param_date") java.sql.Date tourDate,@Param("param_expire") java.util.Date expireDate);

	@Query("select * from tour_booking where status='Booked' and owner_id = :p_owner and tour_date >= :p_date ")
	public List<TourBookingM> findActiveTourBooking(@Param("p_owner") String owner,@Param("p_date") java.sql.Date tourDate);

}
