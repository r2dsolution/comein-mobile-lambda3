package com.r2dsolution.comein.lambda.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.api.model.FeedMail;
import com.r2dsolution.comein.api.model.FeedMessage;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.business.FeedOTABookingDelegate;


public class FeedOTABookingByDateHandler extends BaseSQSHandler{
	//ObjectMapper map = new ObjectMapper();

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
			BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
			FeedOTABookingDelegate db = factory.initFeedOTABookingDelegate(context);
			FeedMail mail = db.feedOTA(body);
			
				
				db.sendToBookingQueue(mail);
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
