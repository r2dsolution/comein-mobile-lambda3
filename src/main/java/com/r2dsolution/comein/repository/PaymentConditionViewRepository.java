package com.r2dsolution.comein.repository;

import org.springframework.data.repository.Repository;

import com.r2dsolution.comein.entity.view.PaymentConditionView;

public interface PaymentConditionViewRepository  extends Repository<PaymentConditionView,String>{

	public PaymentConditionView findByCompanyId(Long compId);
}
