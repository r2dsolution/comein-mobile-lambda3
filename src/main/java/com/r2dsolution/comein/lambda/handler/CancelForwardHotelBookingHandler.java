package com.r2dsolution.comein.lambda.handler;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.client.AdminCognitoClient;
import com.r2dsolution.comein.config.ComeInConfig;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.repository.BookingInfoRepository;

public class CancelForwardHotelBookingHandler extends BaseGateWayHandler<ComeInConfig,GateWayRequest>{
	
	@Override
	protected Class<ComeInConfig> initGateWayConfig() {
		return ComeInConfig.class;
	}

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context)
			throws Exception {
//		String email = (String) input.getBody().get("email");
//		String fullname = input.getProfile().getFirstname()+" "+input.getProfile().getLastname();
//		
//		String profile_email = input.getProfile().getEmail();
		String bookNO = input.getQuery("bookno");
		
		String ownerId = input.getProfile().getComein_id();
		//String name = input.getProfile().getRef_name();
		
		//AdminCognitoClient client = ctx.getBean(AdminCognitoClient.class);
	
		
		//log("email :"+email);
		//log("name :"+name);
		log("ownerId :"+ownerId);
		log("book-no :"+bookNO);
//		
		 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//		 repo.resetBookInfo(fullname, profile_email, bookNO);
		// repo.updateBookInfo(name,ownerId,ownerId,bookNO);
		 repo.resetRefBookInfo( ownerId, bookNO);
		 
		 log("reset-BookInfo completed");
		 output.put("bookno",bookNO);

		return output;
	}

}