package com.r2dsolution.comein.lambda.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.config.ComeInConfig;
import com.r2dsolution.comein.entity.view.BookedTourTicketView;
import com.r2dsolution.comein.entity.view.TicketView;
import com.r2dsolution.comein.entity.view.TourTicketView;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.model.TourTicket;
import com.r2dsolution.comein.repository.BookedTourTicketViewRepository;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.TourTicketViewRepository;
import com.r2dsolution.comein.util.DateUtils;

public class LoadBookedTourTicketHandler extends BaseGateWayHandler<ComeInConfig,GateWayRequest>{
	
	@Override
	protected Class<ComeInConfig> initGateWayConfig() {
		return ComeInConfig.class;
	}

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context)
			throws Exception {
		BookedTourTicketViewRepository repo = ctx.getBean(BookedTourTicketViewRepository.class);
		
		String code = (String) input.getQuery("code");
		String ownerId = input.getProfile().getComein_id();
		log("param booking-code: "+code);
		BookedTourTicketView v = repo.findByCodeAndOwnerId(code, ownerId);
		output.put("result", ComeInMapper.map(v));
		return output;
	}

}
