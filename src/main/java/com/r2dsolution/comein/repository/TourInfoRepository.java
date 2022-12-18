package com.r2dsolution.comein.repository;

import org.springframework.data.repository.CrudRepository;

import com.r2dsolution.comein.entity.TourCompanyM;
import com.r2dsolution.comein.entity.TourInfoM;

public interface TourInfoRepository extends CrudRepository<TourInfoM, Long> {

}
