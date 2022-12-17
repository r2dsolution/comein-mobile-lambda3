package com.r2dsolution.comein.lambda.handler;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.entity.view.TourTicketView;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.TourTicketViewRepository;
import com.r2dsolution.comein.util.DateUtils;

public class ListTourTicketByDateHandler extends BaseGateWayHandler<GateWayRequest>{

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context)
			throws Exception {
		TourTicketViewRepository repo = ctx.getBean(TourTicketViewRepository.class);
		List<TourTicketView> list = repo.findByTourDate(DateUtils.initSQLDate("2022-05-01"));
		for(TourTicketView v: list) {
			System.out.println("ticket id:"+v.getFirstTicketId());
		}
		output.put("results", ComeInMapper.map(list));
		return output;
	}

}
