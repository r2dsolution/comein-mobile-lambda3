package com.r2dsolution.comein.lambda.handler;

import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.BookingKYCInfoM;
import com.r2dsolution.comein.entity.UserKYCInfoM;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.UserKYCRepository;

public class DeleteBookingKYCHandler extends BaseHandler<GateWayRequest>{
	
	

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		String bookno = input.getQuery("bookno");
		
		//String profile_email = input.getProfile().getEmail();
		String ownerId = input.getProfile().getComein_id();
		BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
		log("book-no: "+bookno+" ,owner-id: "+ownerId);
		Optional<BookingInfoM> bookInfoOpt = repo.findByBookingNoAndOwnerId(bookno, ownerId);
		if (bookInfoOpt.isPresent()) {
			String refId = (String) input.getBody().get("card-id");
			String refType = (String) input.getBody().get("card-type");
			log("refId="+refId+" ownerId="+ownerId);
			BookingInfoM bookInfo = bookInfoOpt.get();
			
			bookInfo.removeBookingKYC(refId);
			repo.save(bookInfo);
			log("delete success");
			BookingInfoM result = repo.findById(bookInfo.getId()).get();
			output.put("hotel-booking", toJson(result));
		}
		
		
		return output;
	}

}