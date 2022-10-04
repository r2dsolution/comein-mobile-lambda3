package com.r2dsolution.comein.business;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.HotelM;
import com.r2dsolution.comein.entity.OTABookingM;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.HotelRepository;
import com.r2dsolution.comein.repository.OTABookingRepository;
import com.r2dsolution.comein.util.DateUtils;

@Component
public class OTA2HotelBookingDelegate extends BusinessDelegate{
	
	@Autowired
	private OTABookingRepository otaBookingRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private BookingInfoRepository bookingInfoRepository;
	
	public void newHotelBooking(Long otaId,Long hotelId,String otaStatus) {
		Optional<OTABookingM> otaOpt = otaBookingRepository.findById(otaId);
		if (otaOpt.isPresent()) {
			Optional<HotelM> hotel = hotelRepository.findById(hotelId);
			if (hotel.isPresent()) {
				OTABookingM ota = otaOpt.get();
				BookingInfoM book = new BookingInfoM();
				AggregateReference<HotelM,Long> hotelRef = AggregateReference.to(hotel.get().getId());
				book.setHotelId(hotelRef);
				book.setBookingNo(ota.getBookingNumber());
				book.setRoomName(ota.getRoomType());
				book.setRoomDesc("");
				book.setCheckin(ota.getCheckinDate());
				book.setCheckout(ota.getCheckoutDate());
				book.setVisitorAdult(ota.getAdult());
				book.setVisitorChild(ota.getChild());
				book.setCreatedDate(DateUtils.nowTimestamp());
				book.setCreatedBy("auto-match");
				book.setUpdatedDate(DateUtils.nowTimestamp());
				book.setUpdatedBy("auto-match");
				book.setOtaBookingId(otaId);
				book.setOtaRefEmail(ota.getEmail());
				book.setRefName(ota.getFirstName()+" "+ota.getLastName());
				book.setOtaRefContact(ota.getContactNo());
				book.setPrice(ota.getPrice());
				book.setStatus(BookingInfoM.STATUS_ACTIVE);
				bookingInfoRepository.save(book);
				
				//ota.setStatus(OTABookingM.STATUS_AUTOMATCH);
				ota.setStatus(otaStatus);
				otaBookingRepository.save(ota);
			};
		}
	}

	public void cancelHotelBooking(String bookno,Long otaBookingId,Long hotelId,String otaStatus) {
		try {
			Optional<OTABookingM> otaOpt = otaBookingRepository.findById(otaBookingId);
			if (otaOpt.isPresent()) {
				OTABookingM ota = otaOpt.get();
				Optional<BookingInfoM> opt = bookingInfoRepository.findByBookingNoAndHotelId(bookno,hotelId);
				if (opt.isPresent()) {
					BookingInfoM bookInfo = opt.get();
					bookInfo.setOtaCancelId(otaBookingId);
					bookInfo.setUpdatedBy("auto-match");
					bookInfo.setUpdatedDate(DateUtils.nowTimestamp());
					bookInfo.setStatus(BookingInfoM.STATUS_CANCEL);
					bookingInfoRepository.save(bookInfo);
					
					//ota.setStatus(OTABookingM.STATUS_AUTOMATCH);
					ota.setStatus(otaStatus);
					otaBookingRepository.save(ota);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
