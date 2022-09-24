package com.r2dsolution.comein.lambda.handler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.business.ViewKycBookingDelegate;
import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.HotelM;
import com.r2dsolution.comein.entity.UserKYCInfoM;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.HotelRepository;
import com.r2dsolution.comein.repository.UserKYCRepository;

public class ViewKYCHotelBookingHandler extends BaseHandler<GateWayRequest>{
	
	

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context)
			throws Exception {	
		
		 try {
//			 String url_pattern = "/hotel-bookings/[a-zA-Z0-9]*";
			
//			 log("path: "+path);
//			 String bookNO = getPathParam(url_pattern,path);
			// 
			
			 String bookNO = input.getQuery("bookno");
			// String email = input.getProfile().getEmail();
			 String ownerId = input.getProfile().getComein_id();
			 log(" - bookNO: "+bookNO+" ,owner-id: "+ownerId);
			
//			 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//			 
//			// Optional<BookingInfoM> opt = repo.findByBookingNoAndEmail(bookNO, email);
//			 Optional<BookingInfoM> opt = repo.findByBookingNoAndOwnerId(bookNO,ownerId);
//			 HotelBooking hotelBook  = null;
//			 if (opt.isPresent()) {
//				 BookingInfoM book = opt.get();
//				 log("book-no: "+book.getBookingNo()+" id: "+book.getId());
//				 hotelBook = toJson(book);
//				  
//			 } 
			 
			 BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
			 ViewKycBookingDelegate bd =  factory.initViewKycBookingDelegate(context);
			 HotelBooking hotelBook  = bd.viewHotelBooking(bookNO, ownerId);
			 
			output.put("result", hotelBook);
			output.put("bookno",bookNO);
			 
			 return output;
		 } catch (Exception e) {
				log("error: "+e.getMessage());
				throw e;
			}
			
		}

	
//	HotelBooking toJson(BookingInfoM book) throws Exception {
//		Long hotelId = book.getHotelId().getId();
//		 HotelRepository hotelRepo = ctx.getBean(HotelRepository.class);
//		 HotelM hotel = hotelRepo.findById(hotelId).get();
//		 HotelBooking  hotelBook = ComeInMapper.map(book, hotel);
//		  
//		  UserKYCRepository kycRepo = ctx.getBean(UserKYCRepository.class);
//		  List<UserKYCInfoM> kycList = kycRepo.findByOwnerId(book.getOwnerId());
//		  log("kycList size: "+kycList.size());
//		 hotelBook = ComeInMapper.map(hotelBook, kycList);
//		 return hotelBook;
//	}
	
		 

}