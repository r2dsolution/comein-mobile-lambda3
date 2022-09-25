package com.r2dsolution.comein.lambda.handler;

import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.business.ViewKycBookingDelegate;
import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.BookingKYCInfoM;
import com.r2dsolution.comein.entity.UserKYCInfoM;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.UserKYCRepository;

public class AddBookingKYCHandler extends BaseGateWayHandler<GateWayRequest>{
	
	

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		try {
			String bookno = input.getQuery("bookno");
			//String profile_email = input.getProfile().getEmail();
			String comeinId = input.getProfile().getComein_id();
			BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
			//log("book-no: "+bookno+" ,profile_email: "+profile_email);
			log("book-no: "+bookno+" ,comein-id: "+comeinId);
			//Optional<BookingInfoM> bookInfoOpt = repo.findByBookingNoAndEmail(bookno, profile_email);
			Optional<BookingInfoM> bookInfoOpt = repo.findByBookingNoAndOwnerId(bookno,comeinId);
			
			if (bookInfoOpt.isPresent()) {
				BookingInfoM bookInfo = bookInfoOpt.get();
				
				UserKYCRepository kycRepo = ctx.getBean(UserKYCRepository.class);
				
				String cardId = (String) input.getBody().get("card-id");
				String cardType = (String) input.getBody().get("card-type");
				log("cardId: "+cardId+" , cardType: "+cardType);
				
				UserKYCInfoM user = kycRepo.findByRefIdAndRefTypeAndOwnerId(cardId, cardType, comeinId);
				if (user!=null) {
					//log("user firstname: "+user.getFirstname());
					log("user ref-id: "+user.getRefId()+", ref-type: "+user.getRefType());
					BookingKYCInfoM bookKYC = new BookingKYCInfoM(user);
					
					bookInfo.addBookingKYC(bookKYC);
					repo.save(bookInfo);
					log("update success");
					
//					BookingInfoM result = repo.findById(bookInfo.getId()).get();
//					HotelBooking book = ComeInMapper.map(result, null);
					
					 BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
					 ViewKycBookingDelegate bd =  factory.initViewKycBookingDelegate(context);
					 HotelBooking book  = bd.viewHotelBooking(bookno, comeinId);
					
					//output.put("hotel-booking",result);
					output.put("result",book);
					output.put("bookno",bookno);
				}
			}
			return output;
		} catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

}