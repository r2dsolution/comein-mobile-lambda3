package com.r2dsolution.comein.lambda.handler;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.business.AutoMatchOTADelegate;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.client.SimpleQueueServiceClient;
import com.r2dsolution.comein.model.AutoMatchOTARequest;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.model.HotelBookingRequest;

import reactor.core.publisher.Flux;

public class AutoMatchOTABookingHandler extends BaseSQSHandler{
	
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected SQSEvent doHandleRequest(SQSEvent event, Context context) {
		for(SQSMessage message: event.getRecords()) {
			String body = message.getBody();
			try {
				log("body request: "+body);
				AutoMatchOTARequest req = mapper.readValue(body, AutoMatchOTARequest.class);
				
				BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
				AutoMatchOTADelegate delegate  = factory.initAutoMatchOTADelegate(context);
				Flux<HotelBookingRequest> list = delegate.findByHotelNameRequest(req.getHotelName(),new Long(req.getHotelId()),req.isCancel());
				
				SimpleQueueServiceClient client = ctx.getBean(SimpleQueueServiceClient.class);
				AmazonSQS sqsClient = client.initClient();
				String url = client.urlHotelBooking(sqsClient);
				
				list.subscribe(hotelReq->client.send(sqsClient, url, hotelReq));
				//list.doOnNext(book->doSendQueue(book));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return event;
	}

	

}
