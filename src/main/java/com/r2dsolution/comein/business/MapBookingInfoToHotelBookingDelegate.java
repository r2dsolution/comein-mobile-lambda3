package com.r2dsolution.comein.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.HotelM;
import com.r2dsolution.comein.model.ComeInMapper;
import com.r2dsolution.comein.model.HotelBooking;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.HotelRepository;

import reactor.core.publisher.Flux;

@Component
public class MapBookingInfoToHotelBookingDelegate extends BusinessDelegate{
	
	@Autowired
	HotelRepository hotelRepository;

	public Flux<HotelBooking> mapHotelBooking(List<BookingInfoM> bookList) {
		Flux<BookingInfoM> books = Flux.fromIterable(bookList);
		Flux<HotelBooking> hotelBooks = books.map(b->mapHotelBooking(b))
											.filter(b -> b!=null);
		return hotelBooks;
//		for(BookingInfoM book: books) {
//
//			log("book-id:"+book.getId());
//			Long hotelId = book.getHotelId().getId();
//			log("hotel-id: "+hotelId);
//////			doCache(hotelId,hotelRepo,HotelM.class);
//////			HotelM hotel = getCache(hotelId,HotelM.class);
//			HotelM hotel = null;
//			Optional<HotelM> hotelOpt = hotelRepository.findById(hotelId);
//			if (hotelOpt.isPresent()) {
//				hotel = hotelOpt.get();
//			}
//			//HotelM hotel = hotels.get(hotelId);
//			if (hotel!=null) {
//				log("book-no: "+book.getBookingNo()+" hotel-name: "+hotel.getHotelName());
//				HotelBooking result = ComeInMapper.map(book,hotel);
//				results.add(result);
//			}
//		}
	}

	private HotelBooking mapHotelBooking(BookingInfoM book) {
		log("book-id:"+book.getId());
		Long hotelId = book.getHotelId().getId();
		log("hotel-id: "+hotelId);
////		doCache(hotelId,hotelRepo,HotelM.class);
////		HotelM hotel = getCache(hotelId,HotelM.class);
		HotelM hotel = null;
		Optional<HotelM> hotelOpt = hotelRepository.findById(hotelId);
		if (hotelOpt.isPresent()) {
			hotel = hotelOpt.get();
		}
		//HotelM hotel = hotels.get(hotelId);
		if (hotel!=null) {
			log("book-no: "+book.getBookingNo()+" hotel-name: "+hotel.getHotelName());
			HotelBooking result = ComeInMapper.map(book,hotel);
			return result;
		} else {
			return null;
		}
	}
}
