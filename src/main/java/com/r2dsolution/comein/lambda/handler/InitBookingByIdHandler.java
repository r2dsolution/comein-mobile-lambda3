package com.r2dsolution.comein.lambda.handler;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.business.InitBookingByIdDelegate;
import com.r2dsolution.comein.lambda.model.GateWayRequest;

public class InitBookingByIdHandler extends BaseGateWayHandler<GateWayRequest>{

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		
		
		 try {
			 BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
			 InitBookingByIdDelegate delete = factory.initInitBookingByIdDelegate(context);
			 
			 List<Integer> idList = (List<Integer>) input.getBody().get("id-list");
			 log(idList.toString());
			 String ownerId = input.getProfile().getComein_id();
			 
			 delete.initBookingById(idList, ownerId);
			 return output;
		 } catch (Exception e) {
				e.printStackTrace();
				log("error: "+e.getMessage());
				throw e;
			}
			
		}
		 

}
