package com.r2dsolution.comein.repository;

import java.math.BigDecimal;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.r2dsolution.comein.entity.view.TopUpRateView;


public interface TopUpRateViewRepository  extends Repository<TopUpRateView,String>{

	@Query(name="query.topup")
	public TopUpRateView getTopUpRate(@Param("param_value") BigDecimal pValue,@Param("param_company") Long pCompany);
	
}
