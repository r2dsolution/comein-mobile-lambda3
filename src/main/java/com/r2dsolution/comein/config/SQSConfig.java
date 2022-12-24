package com.r2dsolution.comein.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.r2dsolution.comein.util.SecretManagerUtils;

@Configuration
@PropertySource("classpath:aws.properties")
@PropertySource("classpath:comein.properties")
@ComponentScan({ "com.r2dsolution.comein.client.sqs"})
public class SQSConfig extends BaseConfig{
	
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
    AmazonSQSClientBuilder intAmazonSQSClientBuilder(AWSSecretsManager secretManager) {
    	Map<String,String> awsSecrets = SecretManagerUtils.getSecret(secretManager, mode+"/sqs/comein");
    	String accessKey = awsSecrets.get("accessKey");
		String secretKey = awsSecrets.get("secretKey");
		
		
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
    	
    	return  AmazonSQSClientBuilder.standard()
		          .withCredentials(new AWSStaticCredentialsProvider(awsCreds)) ;
    }
   

}
