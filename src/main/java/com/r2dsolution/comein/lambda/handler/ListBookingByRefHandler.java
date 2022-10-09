package com.r2dsolution.comein.lambda.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.business.ListBookingInfoByRefDelegate;
import com.r2dsolution.comein.business.MapBookingInfoToHotelBookingDelegate;
import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.HotelBooking;

import reactor.core.publisher.Flux;

public class ListBookingByRefHandler extends BaseGateWayHandler<GateWayRequest>{

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		try {
			 
			 
			BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
			ListBookingInfoByRefDelegate delegate = factory.initListBookingInfoByRefDelegate(context);
			List<BookingInfoM> bookList = delegate.getBookingInfo(input.getProfile().getEmail(), input.getProfile().getRef_name());
			
			MapBookingInfoToHotelBookingDelegate map = factory.initMapBookingInfoToHotelBookingDelegate(context);
			Flux<HotelBooking> results = map.mapHotelBooking(bookList);
			
			 
//			for(BookingInfoM book: bookList) {
//				Long hotelId = book.getHotelId().getId();
//				doCache(hotelId,hotelRepo,HotelM.class);
//				HotelM hotel = getCache(hotelId,HotelM.class);
//				//HotelM hotel = hotels.get(hotelId);
//				log("book-no: "+book.getBookingNo()+" hotel-name: "+hotel.getHotelName());
//				HotelBooking result = ComeInMapper.map(book,hotel);
//				results.add(result);
//			}
			output.put("email", input.getProfile().getEmail());
			output.put("ref_name", input.getProfile().getRef_name());
			
			Map<String,Object> bookings = toResult("hotel-booking",results.collectList().block());
			 output.put("result", bookings);
			 output.put("size", bookList.size());
			 return output;
		} catch (Exception e) {
			log("error: "+e.getMessage());
			throw e;
		}
	}

}
