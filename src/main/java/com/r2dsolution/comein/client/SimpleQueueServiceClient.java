package com.r2dsolution.comein.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.api.model.FeedBooking;
import com.r2dsolution.comein.model.EmailRequest;

@Service
public class SimpleQueueServiceClient {
	
	@Value( "${comein.sqs.region}" )
	private String region;
	
	@Value( "${comein.sqs.PDPAInvite.linkURL}" )
	private String linkURL;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private AmazonSQSClientBuilder clientBuilder;
	
	public String queueUrl(AmazonSQS client,String queue) {
		System.out.println("queue name: "+queue);
		GetQueueUrlRequest req = new GetQueueUrlRequest();
		req.withQueueName(queue);
				
		GetQueueUrlResult getQueueUrlResponse = client.getQueueUrl(req);
		
		String queueUrl = getQueueUrlResponse.getQueueUrl();
		System.out.println("queue URL: "+queueUrl);
		return queueUrl;
	}
	public AmazonSQS initClient() {
		AmazonSQS client = clientBuilder.withRegion(region).build();
		return client;
	}
	public void sendMessage(AmazonSQS client,String url,String message) {
		
		SendMessageRequest req = new SendMessageRequest();
		req.withMessageBody(message);
		
		client.sendMessage(url, message);
	}
	
	public void sendFeedBooking(AmazonSQS sqlClient,FeedBooking book) {
		String url = queueUrl(sqlClient, "FeedBookingQueue");
		sendMessage(sqlClient, url, modelToMessage(book));
	}
	
	public void sendMessage(EmailRequest req) {
		req.getParams().put("url", linkURL);
		req.setTemplate("PDPAInvite");
		AmazonSQS sqlClient =initClient();
		String url = queueUrl(sqlClient, "SendEmailQueue");
		sendMessage(sqlClient, url, modelToMessage(req));
	}
	
	protected String modelToMessage(Object req){
		
		try {
			return mapper.writeValueAsString(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

}
