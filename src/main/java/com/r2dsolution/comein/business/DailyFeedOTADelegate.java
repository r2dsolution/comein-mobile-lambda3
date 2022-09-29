package com.r2dsolution.comein.business;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.r2dsolution.comein.client.SimpleQueueServiceClient;

@Component
public class DailyFeedOTADelegate  extends BusinessDelegate{

	@Autowired
	private SimpleQueueServiceClient client;
	
	public void dailyFeed() {
		Calendar cal = Calendar.getInstance(Locale.ENGLISH);
		System.out.println("date="+cal.getTime());
		client.dailyFeed(cal.getTime());
	}
}
