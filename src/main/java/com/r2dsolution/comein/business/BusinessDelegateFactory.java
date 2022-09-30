package com.r2dsolution.comein.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.Context;

@Service
public class BusinessDelegateFactory {
	
	@Autowired
	private ViewKycBookingDelegate viewKycBookingDelegate;
	
	@Autowired
	private SendPDPAInviteDelegate sendPDPAInviteDelegate;
	
	@Autowired
	private FeedOTABookingDelegate feedOTABookingDelegate;
	
	@Autowired
	private DailyFeedOTADelegate dailyFeedOTADelegate;
	
	@Autowired
	private AutoMatchOTADelegate autoMatchOTADelegate;
	
	@Autowired
	private OTA2HotelBookingDelegate ota2HotelBookingDelegate;



	public ViewKycBookingDelegate initViewKycBookingDelegate(Context ctx) {
		viewKycBookingDelegate.initDelegate(ctx);
		return viewKycBookingDelegate;
	}
	
	public SendPDPAInviteDelegate initSendPDPAInviteDelegate(Context ctx) {
		sendPDPAInviteDelegate.initDelegate(ctx);
		return sendPDPAInviteDelegate;
		
	}
	
	public FeedOTABookingDelegate initFeedOTABookingDelegate(Context ctx) {
		feedOTABookingDelegate.initDelegate(ctx);
		return feedOTABookingDelegate;
		
	}
	
	public DailyFeedOTADelegate initDailyFeedOTADelegate(Context ctx) {
		dailyFeedOTADelegate.initDelegate(ctx);
		return dailyFeedOTADelegate;
	}

	public AutoMatchOTADelegate initAutoMatchOTADelegate(Context context) {
		autoMatchOTADelegate.initDelegate(context);
		return autoMatchOTADelegate;
	}
	
	public OTA2HotelBookingDelegate initOTA2HotelBookingDelegate(Context context) {
		ota2HotelBookingDelegate.initDelegate(context);
		return ota2HotelBookingDelegate;
	}
}
