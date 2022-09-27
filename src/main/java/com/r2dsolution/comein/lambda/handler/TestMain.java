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

import com.r2dsolution.comein.api.model.FeedMail;

public class TestMain {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		
		String endpoint = "http://comeinapi-env.eba-xpdwuwiv.ap-southeast-1.elasticbeanstalk.com/mail/message?date=2022-01-01&isBooking=true";
		String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im5vLXJlcGx5QHRoZWNvbWVpbi5jb20iLCJpYXQiOjE2NjQyMTA0ODR9.c1LpkDPncCRUQRX4IBSg1-Ng-FnBs7_9K4T1jiDdi0o";
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("date", "2022-01-01");
		params.put("isBooking", "true");
		
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//		map.add("date", "2022-01-01");
//		map.add("isBooking", "true");
		//map.add("Bearer", accessToken);
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		headers.add("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im5vLXJlcGx5QHRoZWNvbWVpbi5jb20iLCJpYXQiOjE2NjQyMTEwMDV9.-Wzt50e11E8dfZIgELsnk8zdvajAhJLumFso0Y3Xxq4");
		//ResponseEntity<FeedMail> result = restTemplate.getForEntity(endpoint, FeedMail.class, params);
		ResponseEntity<FeedMail> result = restTemplate.exchange(endpoint,HttpMethod.GET,request, FeedMail.class);
		FeedMail content = result.getBody();
		System.out.println("count: "+content.data.count);
		System.out.println("q: "+content.data.option.q);

	}

}
