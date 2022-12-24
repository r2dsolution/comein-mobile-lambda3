package com.r2dsolution.comein.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.sqs.AmazonSQS;
import com.r2dsolution.comein.api.model.FeedMail;
import com.r2dsolution.comein.api.model.FeedMessage;
import com.r2dsolution.comein.client.sqs.SimpleQueueServiceClient;
import com.r2dsolution.comein.entity.OTADailyReport;
import com.r2dsolution.comein.model.FeedBookingRequest;
import com.r2dsolution.comein.repository.OTADailyReportRepository;
import com.r2dsolution.comein.util.DateUtils;

@Component
public class FeedOTABookingDelegate  extends BusinessDelegate{
	
	@Value( "${comein.api.ota.endpoint}" )
	private String endpoint;
	
	@Autowired
	private String loginAccessToken;
	
	@Autowired
	private SimpleQueueServiceClient client;
	
	@Autowired
	private OTADailyReportRepository otaDailyReportRepository;
	
	
	
	public FeedMail feedOTA(String dateStr) {
		log("accessToken: "+loginAccessToken);
		log("date: "+dateStr);
//		Calendar cal = Calendar.getInstance(Locale.ENGLISH);
//		
//		Date date = cal.getTime();
//		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String dateStr = dFormat.format(date);
		
		RestTemplate restTemplate = new RestTemplate();
		
		MultiValueMap<String, String> content = new LinkedMultiValueMap<String, String>();
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im5vLXJlcGx5QHRoZWNvbWVpbi5jb20iLCJpYXQiOjE2NjQyMTEwMDV9.-Wzt50e11E8dfZIgELsnk8zdvajAhJLumFso0Y3Xxq4");
		headers.add("Authorization","Bearer "+loginAccessToken);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(content, headers);
		
		String query = "?isBooking=true&date="+dateStr;
		
		ResponseEntity<FeedMail> result = restTemplate.exchange(endpoint+query,HttpMethod.GET,request, FeedMail.class);
		FeedMail body = result.getBody();
		log("message count: "+body.data.count);
		
		OTADailyReport report = new OTADailyReport();
		report.setCreatedBy("auto-match-engine");
		report.setCreatedDate(DateUtils.nowTimestamp());
		report.setFeedDate(dateStr);
		report.setMailCount(body.data.count);
		otaDailyReportRepository.save(report);
		
		return body;
	}







//	public void sendToBookingQueue(String date,FeedMail mail) {
//		AmazonSQS aSQS = client.initClient();
//		for(FeedMessage m:mail.data.messages) {
//			FeedBookingRequest req = new FeedBookingRequest(date,m.json);
//			client.sendFeedBooking(aSQS, req);
//		}
//		
//	}

	

}
