package com.r2dsolution.comein.lambda.handler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.api.model.FeedBooking;
import com.r2dsolution.comein.entity.OTABookingM;
import com.r2dsolution.comein.repository.OTABookingRepository;
import com.r2dsolution.comein.util.DateUtils;

public class AddUpdateOTABookingHandler extends BaseSQSHandler{
	
	ObjectMapper map = new ObjectMapper();

	@Override
	protected SQSEvent doHandleRequest(SQSEvent event, Context context) {
		for(SQSMessage message: event.getRecords()){
			
			String body = message.getBody();
			doHandler(body,context);
			
		}
		return event;
	}

	protected void doHandler(String body,Context context) {
		try {
			FeedBooking booking = map.readValue(body, FeedBooking.class);
			OTABookingRepository repo = ctx.getBean(OTABookingRepository.class);
			System.out.println("book-no: "+booking.bookingNumber);
			Optional<OTABookingM> opt =	repo.findByBookingNumber(booking.bookingNumber);
			if (!opt.isPresent()) {
				System.out.println("save book-no: "+booking.bookingNumber);
				OTABookingM bookM = doModel(booking);
				bookM = repo.save(bookM);
				System.out.println("save id: "+bookM.getId());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private OTABookingM doModel(FeedBooking booking) {
		OTABookingM entity = new OTABookingM();
		entity.setBookingNumber(booking.bookingNumber);
		entity.setCheckinDate(DateUtils.initSQLDate(booking.checkInDate));
		entity.setCheckoutDate(DateUtils.initSQLDate(booking.checkOutDate));
		entity.setEmail(booking.email);
		entity.setHotelName(booking.hotelName);
		if (booking.dateReceive!=null && !booking.dateReceive.trim().equals("")) {
			entity.setDateReceive(DateUtils.initTimestamp(booking.dateReceive));
		};
		entity.setCreatedDate(new Timestamp(new Date().getTime()));
		entity.setFirstName(booking.firstName);
		entity.setLastName(booking.lastName);
		if (booking.adult!=0) {
			entity.setAdult(booking.adult);
		};
		if (booking.child!=null) {
			entity.setChild(booking.child.intValue());;
		}
		entity.setBooking(booking.isBooking);
		entity.setCancel(booking.isCancel);
		if (booking.contactNo!=null) {
			entity.setContactNo(booking.contactNo);
		} else {
			entity.setContactNo("");
		}
		if (booking.nationality!=null) {
			entity.setNationality(booking.nationality);
		} else {
			entity.setNationality("");
		}
		entity.setPrice(booking.price);
		if (booking.roomNight!=0) {
			entity.setRoomNight(booking.roomNight);
		};
		entity.setRoomType(booking.roomType);
		entity.setStatus("MATCH");
		entity.setTemplateLogic(booking.templateLogic);
		return entity;
	}

}
