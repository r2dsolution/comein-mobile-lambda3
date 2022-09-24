package com.r2dsolution.comein.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.HotelM;
import com.r2dsolution.comein.entity.UserKYCInfoM;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.HotelRepository;
import com.r2dsolution.comein.repository.UserKYCRepository;

@Component
public class ViewKycBookingDelegate extends BusinessDelegate{
	
	@Autowired
	private BookingInfoRepository bookingInfoRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private UserKYCRepository userKYCRepository;
	
	
	
	public HotelBooking viewHotelBooking(String bookNO,String ownerId) throws Exception {
		 Optional<BookingInfoM> opt = bookingInfoRepository.findByBookingNoAndOwnerId(bookNO,ownerId);
		 HotelBooking hotelBook  = null;
		 if (opt.isPresent()) {
			 BookingInfoM book = opt.get();
			 log("book-no: "+book.getBookingNo()+" id: "+book.getId());
			 hotelBook = toJson(book);
			  
		 } ;
		 return hotelBook;
	}
	

	HotelBooking toJson(BookingInfoM book) throws Exception {
		Long hotelId = book.getHotelId().getId();
		
		 HotelM hotel = hotelRepository.findById(hotelId).get();
		 HotelBooking  hotelBook = ComeInMapper.map(book, hotel);
		  
		 
		  List<UserKYCInfoM> kycList = userKYCRepository.findByOwnerId(book.getOwnerId());
		  log("kycList size: "+kycList.size());
		 hotelBook = ComeInMapper.map(hotelBook, kycList);
		 return hotelBook;
	}

}
