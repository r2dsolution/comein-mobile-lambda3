package com.r2dsolution.comein.lambda.handler;

import java.util.Map;
import java.util.Set;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.client.SimpleEmailServiceClient;
import com.r2dsolution.comein.model.EmailRequest;



public class SendEmailByTemplateHandler extends BaseSQSHandler{
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	protected SQSEvent doHandleRequest(SQSEvent event, Context context) {
		SimpleEmailServiceClient client = ctx.getBean(SimpleEmailServiceClient.class);
		AmazonSimpleEmailService emailService = client.initClient();
		
		for(SQSMessage message: event.getRecords()){
			
			EmailRequest request = messageToModel(message,context);
			
			client.sendMail(emailService, request);
			
		}
		return event;
	}
	
	EmailRequest messageToModel(SQSMessage message, Context context){
		EmailRequest req = null;
		try {
			req = mapper.readValue(message.getBody(), EmailRequest.class);
		} catch (Exception e) {
			context.getLogger().log("Error:"+e.getMessage());
		}
		return req;
	}

	
}
