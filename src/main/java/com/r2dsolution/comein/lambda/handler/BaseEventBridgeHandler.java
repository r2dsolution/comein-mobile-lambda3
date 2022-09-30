package com.r2dsolution.comein.lambda.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.r2dsolution.comein.config.ComeInConfig;

public abstract class BaseEventBridgeHandler implements RequestHandler<ScheduledEvent,ScheduledEvent>{
	protected ApplicationContext ctx;
	protected LambdaLogger lambdaLogger;

	@Override
	public ScheduledEvent handleRequest(ScheduledEvent event, Context context) {
		try {
			lambdaLogger = context.getLogger();
			 
			 ctx = new AnnotationConfigApplicationContext(ComeInConfig.class);
			 event = doHandleRequest(event,context);
			 
		}catch(Exception ex) {
			log(ex.getMessage());
		}
		return event;
		
	}

	protected void log(String message) {
		lambdaLogger.log(message);
	}
	protected abstract ScheduledEvent doHandleRequest(ScheduledEvent event, Context context);

}
