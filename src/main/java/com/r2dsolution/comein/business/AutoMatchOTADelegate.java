package com.r2dsolution.comein.business;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.r2dsolution.comein.api.model.FeedBooking;
import com.r2dsolution.comein.client.SimpleQueueServiceClient;
import com.r2dsolution.comein.entity.OTABookingM;
import com.r2dsolution.comein.entity.OTAMatchingRule;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.model.HotelBookingRequest;
import com.r2dsolution.comein.repository.OTABookingRepository;
import com.r2dsolution.comein.repository.OTAMatchingRuleRepository;

import reactor.core.publisher.Flux;

@Component
public class AutoMatchOTADelegate extends BusinessDelegate{
	
	@Autowired
	private OTABookingRepository otaBookingRepository;
	
	@Autowired
	private OTAMatchingRuleRepository otaMatchingRuleRepository;
	
	
	public Flux<HotelBookingRequest> findByHotelId(Long hotelId,boolean isCancel) throws Exception{
		try {
		
			log("find fules by hotel-id: "+hotelId);
			List<OTAMatchingRule> rules = otaMatchingRuleRepository.findByHotelId(hotelId);
			
			Flux<OTAMatchingRule> otaRules = Flux.fromIterable(rules);
			
			Flux<OTABookingM> bookings = otaRules.flatMap(r -> doQuery(r,isCancel) );
			
			//List<OTABookingM> list = otaBookingRepository.findByHotelNameAndStatusAndIsCancelAndIsBookingIsTrue(hotelName,"UNMATCH",isCancel);
			//log("results size: "+list.size());
			//Flux<OTABookingM> bookings = Flux.fromIterable(list);
			
			Flux<HotelBookingRequest> hotelBookings = bookings.map(booking-> doMap(booking,hotelId,isCancel));
			return hotelBookings;
		} catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	private Flux<OTABookingM> doQuery(OTAMatchingRule _rule,boolean _cancel) {
		log("find ota-booking by hotel-name: "+_rule.getHotelName()+" cancel-flag: "+_cancel);
		List<OTABookingM> list = otaBookingRepository.findByHotelNameAndStatusAndIsCancelAndIsBookingIsTrue(_rule.getHotelName(),"UnMatch",_cancel);
		log("result-size ota-booking : "+list.size());
		Flux<OTABookingM> bookings = Flux.fromIterable(list);
		return bookings;
	}

	public Flux<HotelBookingRequest> findByHotelNameRequest(String hotelName,Long hotelId,boolean isCancel){
		List<OTABookingM> list = otaBookingRepository.findByHotelNameAndStatusAndIsCancelAndIsBookingIsTrue(hotelName,"UnMatch",isCancel);
		log("results size: "+list.size());
		Flux<OTABookingM> bookings = Flux.fromIterable(list);
		Flux<HotelBookingRequest> hotelBookings = bookings.map(booking-> doMap(booking,hotelId,isCancel));
		return hotelBookings;
	}

	

	private HotelBookingRequest doMap(OTABookingM booking,Long hotelId,boolean isCancel) {
		log("HotelBookingRequest - booking-no: "+booking.getBookingNumber()+" to hotel-id: "+hotelId);
		HotelBookingRequest req = new HotelBookingRequest();
		req.setOtaBookingId(booking.getId());
		req.setBookingNumber(booking.getBookingNumber());
		req.setHotelId(hotelId);
		req.setCancel(isCancel);
		req.setOtaStatus(OTABookingM.STATUS_AUTOMATCH);
		return req;
	}
	
	

}
