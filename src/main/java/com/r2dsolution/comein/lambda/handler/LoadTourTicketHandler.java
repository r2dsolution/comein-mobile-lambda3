package com.r2dsolution.comein.lambda.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.entity.view.TourTicketView;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.TourTicketViewRepository;
import com.r2dsolution.comein.util.DateUtils;

public class LoadTourTicketHandler extends BaseGateWayHandler<GateWayRequest>{

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context)
			throws Exception {
		TourTicketViewRepository repo = ctx.getBean(TourTicketViewRepository.class);
		
		String tourDateStr = (String) input.getBody().get("tour-date");
		int tourIdInt = (int) input.getBody().get("tour-id");
		log("param tour-date: "+tourDateStr);
		log("param tour-id: "+tourIdInt);
		TourTicketView v = repo.findByTourDateAndTourId(DateUtils.initSQLDate(tourDateStr), new Long(tourIdInt));
		output.put("result", ComeInMapper.map(v));
		return output;
	}

}
