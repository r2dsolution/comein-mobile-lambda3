package com.r2dsolution.comein.lambda.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolPreSignUpEvent;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.business.SendPDPAInviteDelegate;
import com.r2dsolution.comein.client.cognito.AdminCognitoClient;
import com.r2dsolution.comein.lambda.model.CognitoRequest;

@Component
public class SignUpPrePDPAInfoHandler extends BaseCognitoHandler<CognitoUserPoolPreSignUpEvent>{

	@Override
	protected CognitoUserPoolPreSignUpEvent doHandleRequest(CognitoUserPoolPreSignUpEvent request,Context context) {
		log("Start SignUp PAPD........by event\n");
		log("username: "+request.getUserName());
		String username = request.getUserName();
		String cardId = request.getRequest().getUserAttributes().get(AdminCognitoClient.ATTRIBUTE_COMEIN_CARD_ID);
		String email = request.getRequest().getUserAttributes().get(AdminCognitoClient.ATTRIBUTE_EMAIL);
		String secret = cardId.substring(cardId.length()-6);
		
		BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
		SendPDPAInviteDelegate bd =	factory.initSendPDPAInviteDelegate(context);
		bd.invitePDPA(email,username,secret);
		
		log("Finish SignUp PAPD........by event");
		return request;
	}

//	@Override
//	public CognitoUserPoolPreSignUpEvent doHandleRequest(CognitoUserPoolPreSignUpEvent request) {
//		context.getLogger().log("Start SignUp PAPD........by event");
////		CognitoUserPoolPreSignUpEvent.Response response = new CognitoUserPoolPreSignUpEvent.Response();
////		response.setAutoConfirmUser(false);
////		response.setAutoVerifyEmail(false);
////		response.setAutoVerifyPhone(false);
//		context.getLogger().log("username: "+request.getUserName());
//		 BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
//		context.getLogger().log("Finish SignUp PAPD........by event");
//		//request.getRequest().
//		return request;
//	}

	

}
