package com.r2dsolution.comein.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.r2dsolution.comein.entity.BookingInfoM;


public interface BookingInfoRepository extends CrudRepository<BookingInfoM, Long> {

	//public List<BookingInfoM> findByEmail(String email) throws Exception;
	public List<BookingInfoM> findByOwnerId(String comeinId) throws Exception;
	public Optional<BookingInfoM> findByBookingNoAndOwnerId(String bookNO, String ownerId)throws Exception;
	public Optional<BookingInfoM> findByOtaBookingId(Long otaBookingId)throws Exception;
	public Optional<BookingInfoM> findByBookingNo(String bookno)throws Exception;
	//public Optional<BookingInfoM> findByBookingNoAndHotelId(String bookno,Long hotelId)throws Exception;
	
//	public List<BookingInfoM> findByRefNameAndOwnerIdIsNull(String refname) throws Exception;
//	public List<BookingInfoM> findByRefName2(String refname)throws Exception;
//	//public List<BookingInfoM> findByEmailOrCustomerEmail(String email,String customer_email) throws Exception;
//	public List<BookingInfoM> findByOwnerIdOrTargetId(String owner,String target) throws Exception;
//	
//	//public Optional<BookingInfoM> findByBookingNoAndEmail(String bookNO,String email) throws Exception;
//	public Optional<BookingInfoM> findByBookingNoAndOwnerId(String bookNO,String comeinId) throws Exception;
//	
////	@Modifying
////	@Query("update booking_info set customer_email = :p_email, customer_fullname = :p_name where booking_no = :bookno and email = :email")
////	void updateBookInfo(@Param("p_email") String customer_email, @Param("p_name") String customer_name
////			,@Param("email") String email , @Param("bookno") String bookno );
////	
////	
//
//	
//	@Modifying
//	@Query("update booking_info set owner_id = :p_owner, ref_name2 = NULL,ref_name = COALESCE(ref_name2,ref_name) where id = :p_id")
//	void updateBookInfoById(@Param("p_owner") String owner_id,@Param("p_id")Long id);
//	
	@Modifying
	@Query("update booking_info set ref_name2 = :p_name where booking_no = :p_bookno and owner_id = :p_owner")
	void updateRefBookInfo(@Param("p_name") String ref_name, @Param("p_owner") String owner_id , @Param("p_bookno") String bookno );
	
	@Modifying
	@Query("update booking_info set ref_name2 = NULL where booking_no = :p_bookno and owner_id = :p_owner")
	void resetRefBookInfo( @Param("p_owner") String owner_id , @Param("p_bookno") String bookno );
	
	

	
	
//	@Modifying
//	@Query("update booking_info set customer_email = null, customer_fullname = :p_name where booking_no = :bookno and email = :email")
//	void resetBookInfo( @Param("p_name") String customer_name
//			,@Param("email") String email , @Param("bookno") String bookno );
}
