package com.r2dsolution.comein.lambda.handler;

import java.util.Map;

import com.amazonaws.services.cognitoidp.model.UserType;
import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.client.AdminCognitoClient;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.repository.BookingInfoRepository;

public class ForwardHotelBookingHandler extends BaseHandler<GateWayRequest>{

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context)
			throws Exception {
		
		String email = (String) input.getBody().get("email");
//		AdminCognitoClient client = ctx.getBean(AdminCognitoClient.class);
//		UserType user = client.findByEmail(email);
//		String targetId = null;
//		if (user!=null) {
//		String	targetId = client.getAttr(user, AdminCognitoClient.ATTRIBUTE_COMEIN_ID);
			
//			String name = client.getAttr(user, AdminCognitoClient.ATTRIBUTE_REF_NAME);
//			if (input.getBody().containsKey("name")) {
			String	name = (String) input.getBody().get("name");
		
				
//			}
			//String profile_email = input.getProfile().getEmail();
			String ownerId = input.getProfile().getComein_id();
			//String bookNO = input.getPath("bookNO");
			String bookNO = input.getQuery("bookno");
			log("email :"+email);
			log("name :"+name);
			log("owner-id :"+ownerId);
//			log("target-id :"+targetId);
			log("book-no :"+bookNO);
			
			 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
			// repo.updateBookInfo(email, name, profile_email, bookNO);
			 repo.updateRefBookInfo(name,ownerId, bookNO);
			 
			 log("updateBookInfo completed");
//		}else {
//			log("not found email");
//		}
		
		
		
		

		return output;
	}

	

}