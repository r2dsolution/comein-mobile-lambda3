package com.r2dsolution.comein.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class,CognitoConfig.class})
public class AddKYCInfoConfig implements AbstractComeInConfiguration{  

}
