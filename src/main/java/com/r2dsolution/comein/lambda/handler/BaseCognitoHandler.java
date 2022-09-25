package com.r2dsolution.comein.lambda.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolEvent;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolPreSignUpEvent;
import com.r2dsolution.comein.config.ComeInConfig;


public abstract class BaseCognitoHandler<T extends CognitoUserPoolEvent> implements RequestHandler<T,T>{

	protected ApplicationContext ctx;
	protected LambdaLogger lambdaLogger;
	
	@Override
	public T handleRequest(T request, Context context) {
		try {
			lambdaLogger = context.getLogger();
			 
			 ctx = new AnnotationConfigApplicationContext(ComeInConfig.class);
			 request = doHandleRequest(request,context);
			 
		}catch(Exception ex) {
			log(ex.getMessage());
		}
		return request;
	}
	
	protected void log(String message) {
		lambdaLogger.log(message);
	}

	protected abstract T doHandleRequest(T request,Context ctx);
}
