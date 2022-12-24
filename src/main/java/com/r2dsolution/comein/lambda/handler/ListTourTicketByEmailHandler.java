package com.r2dsolution.comein.lambda.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.r2dsolution.comein.config.ComeInConfig;

import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.HotelM;
import com.r2dsolution.comein.entity.TourBookingM;
import com.r2dsolution.comein.entity.TourCompanyM;
import com.r2dsolution.comein.entity.TourInfoM;
import com.r2dsolution.comein.entity.view.BookedTourTicketView;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.model.TourTicket;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.repository.BookedTourTicketViewRepository;
import com.r2dsolution.comein.repository.BookingInfoRepository;

import com.r2dsolution.comein.repository.HotelRepository;
import com.r2dsolution.comein.repository.TourBookingRepository;
import com.r2dsolution.comein.repository.TourCompanyRepository;
import com.r2dsolution.comein.repository.TourInfoRepository;
import com.r2dsolution.comein.util.DateUtils;


//public class ListBookingByEmailHandler implements RequestHandler<GateWayRequest,GatewayResponse>{

//	@Override
//	public GatewayResponse handleRequest(GateWayRequest input, Context context) {
//		LambdaLogger logger = context.getLogger();
//		logger.log("before - init application context");
//		 ApplicationContext ctx = new AnnotationConfigApplicationContext(ComeInConfig.class);
//		 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//		 HotelRepository hotelRepo = ctx.getBean(HotelRepository.class);
//		 try {
//			String email = input.getProfile().getEmail();
//			logger.log("email:"+email+"\n");
//			List<BookingInfoM> books = repo.findByEmail(email);
//			for(BookingInfoM book: books) {
//				HotelM hotel = hotelRepo.findById(book.getHotelId().getId()).get();
//				logger.log("book-no: "+book.getBookingNo()+" hotel-name: "+hotel.getHotelName()+"\n");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 String json ="";
//		return new GateWayResponse();
//	}

public class ListTourTicketByEmailHandler extends BaseGateWayHandler<ComeInConfig,GateWayRequest>{
	
	@Override
	protected Class<ComeInConfig> initGateWayConfig() {
		return ComeInConfig.class;
	}

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output, Context context) throws Exception{
		
		
		 try {
			 
//			 Map<Long,TourInfoM> tours = new HashMap<Long,TourInfoM>();
//			 Map<Long,TourCompanyM> comps = new HashMap<Long,TourCompanyM>();
//			 TourBookingRepository repo = ctx.getBean(TourBookingRepository.class);
//			 TourCompanyRepository compRepo = ctx.getBean(TourCompanyRepository.class);
//			 TourInfoRepository tourRepo = ctx.getBean(TourInfoRepository.class);
			 
			 BookedTourTicketViewRepository repo = ctx.getBean(BookedTourTicketViewRepository.class);
			 
			 
			 List<TourTicket> results = new ArrayList<TourTicket>();
			 
			String email = input.getProfile().getEmail();
			 String ownerId = input.getProfile().getComein_id();
			 log("email:"+email);
			 log("owner-id:"+ownerId);
			 log("sub:"+input.getProfile().getSub());
			 
			//List<BookingInfoM> books = repo.findByEmailOrCustomerEmail(email,email);
			 java.sql.Date now = DateUtils.nowSQLDate();
			 List<BookedTourTicketView> books = repo.findActiveTourBooking(ownerId, now);
//			 List<Booking> books = repo.findByOwnerId(ownerId);
			 log("books size: "+books.size());
			 for(BookedTourTicketView book: books) {

				log("book-id:"+book.getCode());
				TourTicket result = ComeInMapper.map(book);
				results.add(result);
				//Long hotelId = book.getHotelId().getId();
//				Long tourId = book.getTourId();
//				Long compId = book.getCompanyId();
//				
//				log("tour-id: "+tourId);
//////				doCache(hotelId,hotelRepo,HotelM.class);
//////				HotelM hotel = getCache(hotelId,HotelM.class);
//			
//				TourInfoM tour = tours.get(tourId);
//				if (tour==null) {
//					Optional<TourInfoM> tourOpt = tourRepo.findById(tourId);
//					if (tourOpt.isPresent()) {
//						tour = tourOpt.get();
//						tours.put(tourId, tour);
//					}
//				}
//				
//				TourCompanyM comp = comps.get(compId);
//				if (comp==null) {
//					Optional<TourCompanyM> compOpt = compRepo.findById(compId);
//					if (compOpt.isPresent()) {
//						comp = compOpt.get();
//						comps.put(compId, comp);
//					}
//				}
//				
//				//HotelM hotel = hotels.get(hotelId);
//				if (comp!=null && tour!=null) {
//					log("book-code: "+book.getBookingCode()+" tour-name: "+comp.getCompanyName());
//					TourTicket result = ComeInMapper.map(book,tour,comp);
//					results.add(result);
//				}
			}
			// Map<String,Object> bookings = toResult("hotel-booking",results);
			 //output.put("email", email);
			 output.put("owner-id", ownerId);
			 output.put("results", results);
			 output.put("size", books.size());
			 return output;
		} catch (Exception e) {
			e.printStackTrace();
			log("error: "+e.getMessage());
			throw e;
		}
		
	}

	
}