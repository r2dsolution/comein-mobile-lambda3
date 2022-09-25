package com.r2dsolution.comein.lambda.handler;

import org.springframework.context.ApplicationContext;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class BaseHandler {
	
	protected ApplicationContext ctx;
	protected LambdaLogger lambdaLogger;

}
