package com.impetus.casestudy.microservice.searchuser;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;

@Configuration
@ComponentScan("com.impetus.casestudy.microservice.registeruser.dao")
@PropertySource("classpath:dbconfig.properties")
public class Configurations {

	@Value("${driverClassName}")
	private String driverClassName;
	@Value("${dburl}")
	private String dburl;
	@Value("${dbUsername}")
	private String dbUsername;
	@Value("${dbPassword}")
	private String dbPassword;

	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dburl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		// jdbcTemplate.setExceptionTranslator(exceptionTranslator);
		return jdbcTemplate;
	}

	@Bean
	public com.hazelcast.core.HazelcastInstance getHazelInstance() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getNetworkConfig().addAddress(
				UserSearchConstants.LOCALHOSTURL);
		clientConfig.getNetworkConfig().setConnectionAttemptLimit(10);
		clientConfig.getNetworkConfig().setConnectionAttemptPeriod(24 * 60);
		clientConfig.getNetworkConfig().setConnectionTimeout(1000);
		return HazelcastClient.newHazelcastClient(clientConfig);
	}
}