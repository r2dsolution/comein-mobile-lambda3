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
import com.r2dsolution.comein.api.model.FeedMail;

@Component
public class FeedOTABookingDelegate  extends BusinessDelegate{
	
	@Value( "${comein.api.ota.endpoint}" )
	private String endpoint;
	
	@Autowired
	private String loginAccessToken;
	
	
	
	public void feedOTA(String dateStr) {
		System.out.println("accessToken: "+loginAccessToken);
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
		System.out.println("message count: "+body.data.count);
	}

	

}
