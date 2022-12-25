package com.r2dsolution.comein.lambda.handler;

import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.business.delegate.BusinessDelegateFactory;
import com.r2dsolution.comein.business.delegate.ViewKycBookingDelegate;
import com.r2dsolution.comein.config.ComeInConfig;
import com.r2dsolution.comein.config.DeleteKYCInfoConfig;
import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.UserKYCRepository;

public class DeleteKYCInfoHandler extends BaseGateWayHandler<DeleteKYCInfoConfig,GateWayRequest>{
	
	@Override
	protected Class<DeleteKYCInfoConfig> initGateWayConfig() {
		return DeleteKYCInfoConfig.class;
	}
	
	

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		///String profile_email = input.getProfile().getEmail();
		String ownerId = input.getProfile().getComein_id();
		
		//String email = (String) input.getBody().get("email");
		String refId  = (String) input.getBody().get("ref-id");
		String refType  = (String) input.getBody().get("ref-type");
		
		String bookno = (String) input.getBody().get("book-no");
		log("refId="+refId+" owner="+ownerId);
		
		Object isConfirmObj = (String) input.getBody().get("confirm");
		boolean isConfirm = false;
		if (isConfirmObj!=null) {
			String isConfirmStr = (String)isConfirmObj;
			if (isConfirmStr.trim().equals("Y")) {
				isConfirm = true;
			}
		}
		
		if (isConfirm) {
			BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
			Optional<BookingInfoM> bookInfoOpt = repo.findByBookingNoAndOwnerId(bookno, ownerId);
			if (bookInfoOpt.isPresent()) {
				
				log("refId="+refId+" ownerId="+ownerId);
				BookingInfoM bookInfo = bookInfoOpt.get();
				
				bookInfo.removeBookingKYC(refId);
				repo.save(bookInfo);
				log("delete kyc-booking-info success");
			}
		}
		
		UserKYCRepository kycRepo = ctx.getBean(UserKYCRepository.class);
		kycRepo.deleteKYCByRef(refId,refType,ownerId);
		log("delete kyc-info success");
		
//		 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//		Optional<BookingInfoM> result = repo.findByBookingNoAndOwnerId(bookno, ownerId);
//		if (result.isPresent()) {
//			//output.put("hotel-booking", result.get());
//			output.put("result", result.get());
//		}
		
		 BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
		 ViewKycBookingDelegate bd =  factory.initViewKycBookingDelegate(context);
		 HotelBooking book  = bd.viewHotelBooking(bookno, ownerId);
		 output.put("result",book);
		
		return output;
	}

}