package com.r2dsolution.comein.lambda.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.config.ListTourTicketByDateConfig;
import com.r2dsolution.comein.entity.view.TourTicketView;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.TourTicketViewRepository;
import com.r2dsolution.comein.util.DateUtils;

public class ListTourTicketByDateHandler extends BaseGateWayHandler<ListTourTicketByDateConfig,GateWayRequest>{

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context)
			throws Exception {
		TourTicketViewRepository repo = ctx.getBean(TourTicketViewRepository.class);
		
		List<TourTicketView> list  = new ArrayList<TourTicketView>();
		String tourDateStr = (String) input.getBody().get("tour-date");
		log("param tour-date: "+tourDateStr);
		if (tourDateStr!=null) {
			String province = (String) input.getBody().get("province");
			log("param province: "+province);
			java.sql.Date tourDate = DateUtils.initSQLDate(tourDateStr);
			
			if (tourDate!=null) {
				if (province==null || province.trim().equals("")) {
					log("search by tour-date: "+tourDate);
					list = repo.findByTourDate(tourDate);
				} else {
					log("search by tour-date, province:"+province);
				}
				for(TourTicketView v: list) {
					log("ticket id:"+v.getFirstTicketId());
				}
				
			};
		};
		output.put("results", ComeInMapper.map(list));
		return output;
	}

	@Override
	protected Class<ListTourTicketByDateConfig> initGateWayConfig() {
		return ListTourTicketByDateConfig.class;
	}

}
