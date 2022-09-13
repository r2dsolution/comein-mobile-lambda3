package com.r2dsolution.comein.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.PDPAInviteTokenM;

public interface PDPAInviteTokenRepository extends CrudRepository<PDPAInviteTokenM, Long> {

	Optional<PDPAInviteTokenM> findByComeinIdAndStatus(String comeinId, String statusActive);

}
