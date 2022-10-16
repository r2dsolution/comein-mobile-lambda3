package com.r2dsolution.comein.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.repository.BookingInfoRepository;

import reactor.core.publisher.Flux;

@Component
public class InitBookingByIdDelegate extends BusinessDelegate{

	@Autowired
	private BookingInfoRepository repo;
	
	public void initBookingById(List<Integer> idList,String ownerId) {
		
//		 Object obj = input.getBody();
//		 log(obj.toString());
//		 List<Integer> idList = (List<Integer>) input.getBody().get("id-list");
		 log(idList.toString());
//		 String ownerId = input.getProfile().getComein_id();
		 Flux<Integer> ids = Flux.fromIterable(idList);
		 ids.subscribe(id -> repo.updateBookInfoById(ownerId, new Long( id)));
		 
//		 for(Integer id:idList) {
//			 log("update by id="+id+" ,owner-id="+ownerId);
//			 repo.updateBookInfoById(ownerId, new Long( id));
//		 };
		 
		
	}
}
