package com.r2dsolution.comein.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolPreSignUpEvent;
import com.r2dsolution.comein.lambda.model.CognitoRequest;

public class SignUpPDPAInfoHandler implements RequestHandler<CognitoUserPoolPreSignUpEvent,CognitoUserPoolPreSignUpEvent>{

	@Override
	public CognitoUserPoolPreSignUpEvent handleRequest(CognitoUserPoolPreSignUpEvent request, Context context) {
		context.getLogger().log("Start SignUp PAPD........by event");
//		CognitoUserPoolPreSignUpEvent.Response response = new CognitoUserPoolPreSignUpEvent.Response();
//		response.setAutoConfirmUser(false);
//		response.setAutoVerifyEmail(false);
//		response.setAutoVerifyPhone(false);
		context.getLogger().log("userPoolId: "+request.getUserPoolId());
		context.getLogger().log("Finish SignUp PAPD........by event");
		//request.getRequest().
		return request;
	}

}
