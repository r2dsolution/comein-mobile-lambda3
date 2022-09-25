package com.r2dsolution.comein.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.r2dsolution.comein.lambda.model.CognitoRequest;

public class SignUpPDPAInfoHandler implements RequestHandler<CognitoRequest,CognitoRequest>{

	@Override
	public CognitoRequest handleRequest(CognitoRequest request, Context context) {
		context.getLogger().log("SignUp PAPD........");
		for (String key: request.getDatasetRecords().keySet()) {
			context.getLogger().log("SignUp PAPD -> key: "+key);
		}
		//context.getLogger().log("SignUp PAPD input: "+input);
		return request;
	}

}
