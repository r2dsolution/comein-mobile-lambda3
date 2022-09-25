package com.r2dsolution.comein.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SignUpPDPAInfoHandler implements RequestHandler<String,String>{

	@Override
	public String handleRequest(String input, Context context) {
		context.getLogger().log("SignUp PAPD........");
		context.getLogger().log("SignUp PAPD input: "+input);
		return input;
	}

}
