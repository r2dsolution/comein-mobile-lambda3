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
}
