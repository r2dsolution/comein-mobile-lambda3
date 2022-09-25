package com.r2dsolution.comein.lambda.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.r2dsolution.comein.config.ComeInConfig;


public abstract class BaseSQSHandler implements RequestHandler<SQSEvent,SQSEvent>{
	
	protected ApplicationContext ctx;
	protected LambdaLogger lambdaLogger;

	@Override
	public SQSEvent handleRequest(SQSEvent event, Context context) {
		try {
			lambdaLogger = context.getLogger();
			 
			 ctx = new AnnotationConfigApplicationContext(ComeInConfig.class);
			 event = doHandleRequest(event,context);
			 
		}catch(Exception ex) {
			log(ex.getMessage());
		}
		return event;
	}

	protected abstract SQSEvent doHandleRequest(SQSEvent event, Context context);

	protected void log(String message) {
		lambdaLogger.log(message);
	}
}
