package com.r2dsolution.comein.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.r2dsolution.comein.entity.OTAMatchingRule;

public interface OTAMatchingRuleRepository extends CrudRepository<OTAMatchingRule, Long> {
	
	public List<OTAMatchingRule> findByHotelId(Long hotelId) throws Exception;

}
