package com.r2dsolution.comein.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.r2dsolution.comein.util.SecretManagerUtils;

@Configuration
@Import(DatabaseConfig.class)
@EnableJdbcRepositories("com.r2dsolution.comein.repository.TourTicketViewRepository")
public class ListTourTicketByDateConfig implements AbstractComeInConfiguration{   
	
}
