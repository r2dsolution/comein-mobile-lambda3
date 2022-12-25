package com.r2dsolution.comein.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class,DelegateConfig.class})
public class AddBookingKYCConfig implements AbstractComeInConfiguration{  

}
