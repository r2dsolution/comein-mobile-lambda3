package com.r2dsolution.comein.lambda.handler;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.business.delegate.DelegateFactory;
import com.r2dsolution.comein.business.delegate.InitBookingByIdDelegate;
import com.r2dsolution.comein.config.ComeInConfig;
import com.r2dsolution.comein.config.InitBookingByIdConfig;
import com.r2dsolution.comein.lambda.model.GateWayRequest;

public class InitBookingByIdHandler extends BaseGateWayHandler<InitBookingByIdConfig,GateWayRequest>{
	
	@Override
	protected Class<InitBookingByIdConfig> initGateWayConfig() {
		return InitBookingByIdConfig.class;
	}

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		
		
		 try {
			 DelegateFactory factory = ctx.getBean(DelegateFactory.class);
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
