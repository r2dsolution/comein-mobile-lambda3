package com.r2dsolution.comein.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.repository.*;

@Component
public class ListBookingInfoByRefDelegate extends BusinessDelegate{
	
	@Autowired
	BookingInfoRepository bookingInfoRepository;

	
	public List<BookingInfoM> getBookingInfo(String email,String refname2) throws Exception{
		 
		 
		 
		// List<HotelBooking> results = new ArrayList<HotelBooking>();
		 
//		String email = input.getProfile().getEmail();
//		 String ownerId = input.getProfile().getComein_id();
//		 String refname = input.getProfile().getRef_name();
		 log("email: "+email);
//		 log("owner-id: "+ownerId);
		 log("refname2: "+refname2);
		 
		//List<BookingInfoM> books = repo.findByEmailOrCustomerEmail(email,email);
		 List<BookingInfoM> bookList = new ArrayList<BookingInfoM>();
		 List<BookingInfoM> book1 = bookingInfoRepository.findByReference(email);
		 log("result by email size:"+book1.size());
		 List<BookingInfoM> book2 = bookingInfoRepository.findByRefName2(refname2);
		 log("result by refname size:"+book2.size());
		 bookList.addAll(book1);
		 bookList.addAll(book2);
		 log("result size:"+bookList.size());
		 return bookList;
	}
}
