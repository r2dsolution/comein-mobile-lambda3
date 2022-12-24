package com.r2dsolution.comein.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

public class BaseConfig {
	 public AWSCredentialsProvider initCredentialsProvider(String accessKey,String secretKey) {
	    	
	    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
	    	
	    	
	    	return new AWSStaticCredentialsProvider(awsCreds);
	    }
}
