package com.r2dsolution.comein.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.util.RegionsUtils;
import com.r2dsolution.comein.util.SecretManagerUtils;

import java.util.Base64;
import java.util.HashMap;


@Configuration
@PropertySource("classpath:comein.properties")
@PropertySource("classpath:aws.properties")
@ComponentScan({ "com.r2dsolution.comein.service","com.r2dsolution.comein.business","com.r2dsolution.comein.client"})
@EnableJdbcRepositories("com.r2dsolution.comein.repository")
public class ComeInConfig extends AbstractJdbcConfiguration {   
	
	@Value( "${comein.db.driver}" )
	public String driver;
	
	@Value( "${comein.mode}" )
	public String mode;
//	
//	@Value( "${comein.db.host}" )
//	public String host;
//	
//	@Value( "${comein.db.port}" )
//	public String port;
//	
//	@Value( "${comein.db.username}" )
//	public String username;
//	
//	@Value( "${comein.db.password}" )
//	public String password;
//	
//	@Value( "${comein.db.dbname:abc}" )
//	public String database;
	
	
	@Value( "${accessKey}" )
	public String accessKey;
	
	@Value( "${secretKey}" )
	public String secretKey;
	
	
	@Value( "${region}" )
	public String region;
	
	

	@Bean
    DataSource dataSource(AWSSecretsManager secretManager) {  
		Map<String,String> awsSecrets = SecretManagerUtils.getSecret(secretManager, mode+"/db/postgresql/comein");
		String host = awsSecrets.get("host");
		String port = awsSecrets.get("port");
		String database = awsSecrets.get("dbInstance");
		String username = awsSecrets.get("username");
		String password = awsSecrets.get("password");
		
		String url =  "jdbc:postgresql://"+host+":+"+port+"/"+database;
		System.out.println("url="+url);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
	
    }

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) { 
        return new NamedParameterJdbcTemplate(dataSource);
    }

    
   
    @Bean
    TransactionManager transactionManager(DataSource dataSource) {                     
        return new DataSourceTransactionManager(dataSource);
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
    
//    @Bean
//    public AWSCredentialsProvider initCredentialsProvider() {
//		return new ClasspathPropertiesFileCredentialsProvider("aws.properties");
//	}
    
    @Bean
    public AWSCredentialsProvider initCredentialsProvider() {
    	
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
    	
    	
    	return new AWSStaticCredentialsProvider(awsCreds);
    }

    
    @Bean 
    public AWSSecretsManager initAWSSecretsManager() {
    	Regions region = Regions.AP_SOUTHEAST_1;
    	 AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
					.withCredentials(initCredentialsProvider()) 
                 .withRegion(region)
                 .build();
    	 return client;
    }
    
    
   
}