package com.r2dsolution.comein.client;

import java.util.Date;


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
import com.r2dsolution.comein.model.FeedBookingRequest;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.model.HotelBookingRequest;
import com.r2dsolution.comein.util.DateUtils;

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
	public String urlFeedBooking(AmazonSQS sqlClient) {
		String url = queueUrl(sqlClient, "FeedBookingQueue");
		return url;
	}
	
//	public void sendFeedBooking(AmazonSQS sqlClient,String url,FeedBookingRequest book) {
//	
//		sendMessage(sqlClient, url, modelToMessage(book));
//	}
	
	public String urlHotelBooking(AmazonSQS sqlClient) {
		String url = queueUrl(sqlClient, "HotelBookingQueue");
		return url;
	}
	
//	public void sendHotelBooking(AmazonSQS sqlClient,String url,HotelBookingRequest book) {
//		//String url = queueUrl(sqlClient, "HotelBookingQueue");
//		sendMessage(sqlClient, url, modelToMessage(book));
//	}
	
	public void send(AmazonSQS sqlClient,String url,Object obj) {
		//String url = queueUrl(sqlClient, "HotelBookingQueue");
		sendMessage(sqlClient, url, modelToMessage(obj));
	}
	
	protected void sendMessage(EmailRequest req) {
		req.getParams().put("url", linkURL);
		req.setTemplate("PDPAInvite");
		AmazonSQS sqlClient =initClient();
		String url = queueUrl(sqlClient, "SendEmailQueue");
		sendMessage(sqlClient, url, modelToMessage(req));
	}
	
	public void dailyFeed(Date d) {
		
		AmazonSQS sqsClient =initClient();
		String url = queueUrl(sqsClient, "FeedOTAQueue");
		sendMessage(sqsClient, url, DateUtils.format(d,"yyyy-MM-dd"));
	}
	
	protected String modelToMessage(Object req){
		
		try {
			return mapper.writeValueAsString(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	public String urlSendEmail(AmazonSQS sqsClient) {
		String url = queueUrl(sqsClient, "SendEmailQueue");
		return url;
		// TODO Auto-generated method stub
		
	}

}
