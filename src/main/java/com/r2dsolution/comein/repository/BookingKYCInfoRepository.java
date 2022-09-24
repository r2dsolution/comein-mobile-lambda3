package com.r2dsolution.comein.repository;

import org.springframework.data.repository.CrudRepository;

import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.BookingKYCInfoM;

public interface BookingKYCInfoRepository extends CrudRepository<BookingKYCInfoM, Long> {

}
