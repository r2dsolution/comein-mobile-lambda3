package com.r2dsolution.comein.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.Context;

@Service
public class BusinessDelegateFactory {
	
	@Autowired
	public ViewKycBookingDelegate viewKycBookingDelegate;
	
	



	public ViewKycBookingDelegate initViewKycBookingDelegate(Context ctx) {
		viewKycBookingDelegate.initDelegate(ctx);
		return viewKycBookingDelegate;
	}
}
