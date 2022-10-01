package com.r2dsolution.comein.business;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.r2dsolution.comein.api.model.FeedBooking;
import com.r2dsolution.comein.client.SimpleQueueServiceClient;
import com.r2dsolution.comein.entity.OTABookingM;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.model.HotelBookingRequest;
import com.r2dsolution.comein.repository.OTABookingRepository;

import reactor.core.publisher.Flux;

@Component
public class AutoMatchOTADelegate extends BusinessDelegate{
	
	@Autowired
	private OTABookingRepository otaBookingRepository;
	
	
	
	public Flux<HotelBookingRequest> findByHotelNameRequest(String hotelName,Long hotelId,boolean isCancel){
		List<OTABookingM> list = otaBookingRepository.findByHotelNameAndStatusAndIsCancelAndIsBookingIsTrue(hotelName,"UNMATCH",isCancel);
		log("results size: "+list.size());
		Flux<OTABookingM> bookings = Flux.fromIterable(list);
		Flux<HotelBookingRequest> hotelBookings = bookings.map(booking-> doMap(booking,hotelId,isCancel));
		return hotelBookings;
	}

	

	private HotelBookingRequest doMap(OTABookingM booking,Long hotelId,boolean isCancel) {
		log("map booking-no: "+booking.getBookingNumber()+" to hotel-id: "+hotelId);
		HotelBookingRequest req = new HotelBookingRequest();
		req.setOtaBookingId(booking.getId());
		req.setBookingNumber(booking.getBookingNumber());
		req.setHotelId(hotelId);
		req.setCancel(isCancel);
		return req;
	}
	
	

}
