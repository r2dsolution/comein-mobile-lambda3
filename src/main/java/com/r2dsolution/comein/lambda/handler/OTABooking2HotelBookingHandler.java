package com.r2dsolution.comein.lambda.handler;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.business.OTA2HotelBookingDelegate;
import com.r2dsolution.comein.model.AutoMatchOTARequest;
import com.r2dsolution.comein.model.HotelBookingRequest;

public class OTABooking2HotelBookingHandler  extends BaseSQSHandler{
	
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected SQSEvent doHandleRequest(SQSEvent event, Context context) {
		for(SQSMessage message: event.getRecords()) {
			String body = message.getBody();
			try {
				HotelBookingRequest req = mapper.readValue(body, HotelBookingRequest.class);
				
				BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
				OTA2HotelBookingDelegate delegate = factory.initOTA2HotelBookingDelegate(context);
				if (req.isCancel()) {
					delegate.cancelHotelBooking(req.getBookingNumber(),req.getOtaBookingId());
				} else {
					delegate.newHotelBooking(req.getOtaBookingId(), req.getHotelId());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return event;
	}

}
