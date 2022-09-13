package com.r2dsolution.comein.lambda.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.r2dsolution.comein.config.ComeInConfig;
import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.HotelM;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.HotelRepository;


//public class ListBookingByEmailHandler implements RequestHandler<GateWayRequest,GatewayResponse>{

//	@Override
//	public GatewayResponse handleRequest(GateWayRequest input, Context context) {
//		LambdaLogger logger = context.getLogger();
//		logger.log("before - init application context");
//		 ApplicationContext ctx = new AnnotationConfigApplicationContext(ComeInConfig.class);
//		 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//		 HotelRepository hotelRepo = ctx.getBean(HotelRepository.class);
//		 try {
//			String email = input.getProfile().getEmail();
//			logger.log("email:"+email+"\n");
//			List<BookingInfoM> books = repo.findByEmail(email);
//			for(BookingInfoM book: books) {
//				HotelM hotel = hotelRepo.findById(book.getHotelId().getId()).get();
//				logger.log("book-no: "+book.getBookingNo()+" hotel-name: "+hotel.getHotelName()+"\n");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 String json ="";
//		return new GateWayResponse();
//	}

public class ListBookingByEmailHandler extends BaseHandler<GateWayRequest>{

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		
		
		 try {
			 
			 
			 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
			 HotelRepository hotelRepo = ctx.getBean(HotelRepository.class);
			 
			 
			 List<HotelBooking> results = new ArrayList<HotelBooking>();
			 
			String email = input.getProfile().getEmail();
			 String ownerId = input.getProfile().getComein_id();
			 log("email:"+email);
			 log("owner-id:"+ownerId);
			 log("sub:"+input.getProfile().getSub());
			 
			//List<BookingInfoM> books = repo.findByEmailOrCustomerEmail(email,email);
			 List<BookingInfoM> books = repo.findByOwnerId(ownerId);
			for(BookingInfoM book: books) {
				Long hotelId = book.getHotelId().getId();
//				doCache(hotelId,hotelRepo,HotelM.class);
//				HotelM hotel = getCache(hotelId,HotelM.class);
				HotelM hotel = null;
				Optional<HotelM> hotelOpt = hotelRepo.findById(hotelId);
				if (hotelOpt.isPresent()) {
					hotel = hotelOpt.get();
				}
				//HotelM hotel = hotels.get(hotelId);
				if (hotel!=null) {
					log("book-no: "+book.getBookingNo()+" hotel-name: "+hotel.getHotelName());
					HotelBooking result = ComeInMapper.map(book,hotel);
					results.add(result);
				}
			}
			 //output.put("email", email);
			 output.put("owner-id", ownerId);
			 output.put("hotel-booking", results);
			 output.put("size", books.size());
			 return output;
		} catch (Exception e) {
			log("error: "+e.getMessage());
			throw e;
		}
		
	}

	
}