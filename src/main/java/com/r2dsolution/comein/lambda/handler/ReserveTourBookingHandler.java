package com.r2dsolution.comein.lambda.handler;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.sqs.AmazonSQS;
import com.r2dsolution.comein.client.SimpleQueueServiceClient;
import com.r2dsolution.comein.config.ComeInConfig;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.TourBookingRequest;
import com.r2dsolution.comein.util.StringUtils;

public class ReserveTourBookingHandler extends BaseGateWayHandler<ComeInConfig,GateWayRequest>{
	
	@Override
	protected Class<ComeInConfig> initGateWayConfig() {
		return ComeInConfig.class;
	}
	

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		
		SimpleQueueServiceClient client = ctx.getBean(SimpleQueueServiceClient.class);
		AmazonSQS sqsClient = client.initClient();
		String url = client.urlReserveTourBooking(sqsClient);
		
		
		 String ownerId = input.getProfile().getComein_id();
		 String ref = input.getProfile().getRef_name();
		 
		 int adult = (int) input.getBody().get("adult");
		 int child = (int) input.getBody().get("child");
		 String location = (String) input.getBody().get("special-inst");
		 String remark  = "-";
		 String tourDate = (String) input.getBody().get("tour-date");
		 int tourId =  (int) input.getBody().get("tour-id");
		 
		 String bookingCode = initBookingNO();
		 
		 TourBookingRequest req = new TourBookingRequest();
		 req.setOwnerId(ownerId);
		 req.setRefName(ref);
		 req.setAdult(adult);
		 req.setChild(child);
		 req.setLocation(location);
		 req.setRemark(remark);
		 req.setTourDate(tourDate);
		 req.setTourId(tourId);
		 req.setBookingCode(bookingCode);
		 
		 client.sendFIFO(sqsClient, url, req,"ReserveTourBooking");
		
		return output;
	}

	private String initBookingNO() {
		// TODO Auto-generated method stub
		return StringUtils.randomStr(10);
	}

}
