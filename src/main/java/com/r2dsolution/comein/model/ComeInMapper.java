package com.r2dsolution.comein.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.HotelM;
import com.r2dsolution.comein.entity.UserKYCInfoM;

public class ComeInMapper {
	
	private static final DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

	public static final Hotel map(HotelM hotel) {
		Hotel h = new Hotel();
		h.setName(hotel.getHotelName());
		h.setShortName(hotel.getHotelName().substring(0, 3));
		h.setAddress(hotel.getAddress());
		h.setCountry(hotel.getCountry());
		h.setProvince(hotel.getProvince());
		h.setImage1Url(hotel.getImage1Url()==null?"":hotel.getImage1Url());
		
		return h;
	}
	public static final HotelBooking map(HotelBooking book,List<UserKYCInfoM> list) {
		for(UserKYCInfoM info: list) {
			UserKYC kyc = map(info);
			String _ref = "";
			if (info.getRefType().equals(UserKYCInfoM.EMAIL_REF)) {
				book.getKycEmail().add(kyc.getEmail());
				_ref = kyc.getEmail();
			}else {
				
				_ref = kyc.getRefId();
			}
			book.getKycEmail().add(_ref);
			
			book.getKycInfo().put(_ref, kyc);
		}
		return book;
	}

	public static UserKYC map(UserKYCInfoM info) {
		UserKYC kyc = new UserKYC();
//		kyc.setEmail(info.getEmail());
//		kyc.setFirstname(info.getFirstname());
//		kyc.setLastname(info.getLastname());
		kyc.setRefId(info.getRefId());
		kyc.setRefType(info.getRefType());
		kyc.setDisplayName(info.getRefName());
		return kyc;
	}
	public static final HotelBooking map(BookingInfoM book, HotelM hotel) {
		HotelBooking b = new HotelBooking();
		b.setId(book.getId());
		b.setBookNO(book.getBookingNo());
		if (hotel!=null) {
			b.setHotel(map(hotel));
		};
		b.setRefName(book.getRefName()==null?"":book.getRefName().trim());
		b.setRefName2(book.getRefName2()==null?"":book.getRefName2().trim());
//
//		
		b.setCheckIn(book.getCheckin()==null ? "":dFormat.format(book.getCheckin()));
//		
//		
		b.setCheckOut(book.getCheckout()==null ? "": dFormat.format(book.getCheckout()));
		
		b.setRoomName(book.getRoomName()==null ? "" : book.getRoomName());
		b.setRoomDesc(book.getRoomDesc()==null ? "" : book.getRoomDesc());
		b.setVisitorAdult(book.getVisitorAdult());
		b.setVisitorChild(book.getVisitorChild());
		//b.setCustomerEmail(book.getCustomerEmail()==null ? "": book.getCustomerEmail());
		
//		Set<String> key = book.getKycInfo().keySet();
//		b.setCardId(new ArrayList(key));
		
		return b;
	}
}
