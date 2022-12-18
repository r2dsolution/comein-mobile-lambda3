package com.r2dsolution.comein.lambda.handler;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.business.BusinessDelegateFactory;
import com.r2dsolution.comein.entity.view.PaymentConditionView;
import com.r2dsolution.comein.entity.view.TopUpRateView;
import com.r2dsolution.comein.entity.view.TourTicketView;
import com.r2dsolution.comein.model.HotelBookingRequest;
import com.r2dsolution.comein.model.TourBookingRequest;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.PaymentConditionViewRepository;
import com.r2dsolution.comein.repository.TopUpRateViewRepository;
import com.r2dsolution.comein.repository.TourBookingRepository;
import com.r2dsolution.comein.repository.TourTicketRepository;
import com.r2dsolution.comein.repository.TourTicketViewRepository;
import com.r2dsolution.comein.util.DateUtils;

public class UpdateTourTicketBookingHandler extends BaseSQSHandler{

	@Override
	protected SQSEvent doHandleRequest(SQSEvent event, Context context) {
		
		TourTicketRepository ticketRepo = ctx.getBean(TourTicketRepository.class);
		TourBookingRepository bookingRepo = ctx.getBean(TourBookingRepository.class);
		
		TopUpRateViewRepository topupRepo = ctx.getBean(TopUpRateViewRepository.class);
		TourTicketViewRepository ticketViewRepo = ctx.getBean(TourTicketViewRepository.class);
		
		PaymentConditionViewRepository conditionRepo = ctx.getBean(PaymentConditionViewRepository.class);
		ObjectMapper mapper = new ObjectMapper();
		String status = "Booked";
		//String status = "Pending";

		for(SQSMessage message: event.getRecords()) {
			String body = message.getBody();
			try {
				
				
				TourBookingRequest req = mapper.readValue(body, TourBookingRequest.class);
				int tourIdInt = req.getTourId();
				Long tourId = new Long(tourIdInt);
				
				java.sql.Date tourDate = DateUtils.initSQLDate(req.getTourDate());
				String bookno = req.getBookingCode();
				String location = req.getLocation();
				int adult = req.getAdult();
				int child = req.getChild();
				log("adult= "+adult+" ,child= "+child);
				String remark = req.getRemark();
				
				String userId = req.getOwnerId();
				String refname = req.getRefName();
				
				log("tour-date: "+tourDate);
				//TourTicketView ticket = ticketViewRepo.findByFirstTicketId(new Long(1007));
				TourTicketView ticket = ticketViewRepo.findByTourDateAndTourId(tourDate,tourId);
				if (ticket!=null) {
					log("ticket-no: " +ticket.getTicketCode()+" total: "+ticket.getTicketCount());
					//Random r = new Random();
					
					
					Long companyId = ticket.getCompanyId();
					//java.sql.Date tourDate = ticket.getTourDate(); //DateUtils.initSQLDate("2022-05-01");
					
					
					
					
					BigDecimal sellChild = ticket.getChildRate().multiply(new BigDecimal(child));
					BigDecimal sellAdult =  ticket.getAdultRate().multiply(new BigDecimal(adult));
					BigDecimal sell = sellChild.add(sellAdult);
					BigDecimal net = sell;
					
					BigDecimal hotel = new BigDecimal(0);
					BigDecimal comein = new BigDecimal(0);
					
					
					
					String strHotels = null;
					TopUpRateView topup =  topupRepo.getTopUpRate(sell, companyId);
					if (topup!=null) {
						net = net.add(topup.getTopupRate());
						Set<Long> hotels = findTopupHotel(ticket,userId);
						boolean isTopupHotel = !hotels.isEmpty();
						if (isTopupHotel) {
							
							
						
								strHotels = mapper.writeValueAsString(hotels);
							
			//			} else {
			//				comein = comein.add(topup.getHotelRate());
						}
						hotel = hotel.add(topup.getHotelRate());
						comein = comein.add(topup.getComeinRate());
					}
					log("tour-date:"+tourDate);
					log("hotels = "+strHotels);
					Calendar cal = Calendar.getInstance(Locale.ENGLISH);
					cal.setTime(tourDate);
					PaymentConditionView cond = conditionRepo.findByCompanyId(companyId);
					if (cond!=null) {
						int payableDay = cond.getPayableDay();
						
						cal.add(Calendar.DATE, -payableDay);
					}
					
					java.util.Date expire = cal.getTime();
					log("epire-date:"+expire);
					
					bookingRepo.addTourBooking(bookno, userId, refname, location, adult, child, sell, hotel,comein, net, remark, status, tourId,strHotels, tourDate,expire);
					int ticketSize = ticketRepo.updateBookingTicket(bookno,status,userId,tourId, tourDate);
					log("booking: "+(adult+child)+" booked: "+ticketSize);
				}else {
					log("not found ticket");
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		//ticketViewRepo.findByTourDate(null)
		// param of input
//		Long tourId = new Long(1);
//		int day = r.nextInt(10)+10;
//		java.sql.Date tourDate = DateUtils.initSQLDate("2022-05-"+day);
		
		
//		String userId = "f6aa0030-39ba-11ed-8b2a-f5decceb717a_b658ffa6-69e0-4ecd-8bdd-09e0c6c978ca";
//		String bookno = "B"+10000+r.nextInt(10)*1000+r.nextInt(10)*100+r.nextInt(10)*10+r.nextInt(10);
		
//		String refname = "ref-xxxx";
		
		
	
		
		//
		
		return event;
	}

	
	private Set<Long> findTopupHotel(TourTicketView ticket, String userId) {
		Date tourDate = ticket.getTourDate();
		BookingInfoRepository bookRepo = ctx.getBean(BookingInfoRepository.class);
		Set<Long> hotels = new HashSet<Long>();
		try {
			hotels = bookRepo.findTopupByTourDate(userId, tourDate);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		// TODO Auto-generated method stub
		return hotels;
	}

}
