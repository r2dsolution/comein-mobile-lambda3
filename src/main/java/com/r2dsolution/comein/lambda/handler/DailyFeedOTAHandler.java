package com.r2dsolution.comein.lambda.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.business.DailyFeedOTADelegate;
import com.r2dsolution.comein.business.FeedOTABookingDelegate;
import com.r2dsolution.comein.config.ComeInConfig;

public class DailyFeedOTAHandler implements RequestHandler<ScheduledEvent,ScheduledEvent>{

	@Override
	public ScheduledEvent handleRequest(ScheduledEvent e, Context context) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ComeInConfig.class);
		BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
		DailyFeedOTADelegate db = factory.initDailyFeedOTADelegate(context);
		db.dailyFeed();
		return e;
	}

}
