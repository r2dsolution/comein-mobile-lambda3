package com.r2dsolution.comein.business;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class BusinessDelegate {
	
	private Context context;

	public void initDelegate(Context context) {
		this.context=context;
	}
	
	public void log(String message) {
		context.getLogger().log(message);
		
	}
}
