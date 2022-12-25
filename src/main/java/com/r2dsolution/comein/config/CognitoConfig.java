package com.r2dsolution.comein.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.r2dsolution.comein.util.SecretManagerUtils;

@Configuration
@PropertySource("classpath:aws.properties")
@PropertySource("classpath:comein.properties")
@ComponentScan({ "com.r2dsolution.comein.client.cognito"})
public class CognitoConfig extends BaseConfig{
	
	@Value( "${accessKey}" )
	public String accessKey;
	
	@Value( "${secretKey}" )
	public String secretKey;
	
	
	@Value( "${region}" )
	public String region;
	
	
	@Value( "${comein.mode}" )
	public String mode;
	
	
	@Bean 
    public AWSSecretsManager initAWSSecretsManager() {

    	 AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
					.withCredentials(initCredentialsProvider(accessKey,secretKey)) 
                 .withRegion(region)
                 .build();
    	 return client;
    }
	
    @Bean
    AWSCognitoIdentityProviderClientBuilder initAWSCognitoIdentityProviderClientBuilder(AWSSecretsManager secretManager) {
    	Map<String,String> awsSecrets = SecretManagerUtils.getSecret(secretManager, mode+"/cognito/comein");
    	String _accessKey = awsSecrets.get("accessKey");
    	String _secretKey = awsSecrets.get("secretKey");
    	//String _region = awsSecrets.get("region");
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(_accessKey,_secretKey);
    	return AWSCognitoIdentityProviderClientBuilder.standard()
    			.withCredentials(new AWSStaticCredentialsProvider(awsCreds));
//    			.withRegion(RegionsUtils.initRegions(region));
//				.withCredentials(new ClasspathPropertiesFileCredentialsProvider("aws.properties"));
//				 .withRegion(region);
    }

}
