package com.r2dsolution.comein.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SQSConfig.class)
public class ReserveTourBookingConfig implements AbstractComeInConfiguration{

}
