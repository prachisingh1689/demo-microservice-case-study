package com.impetus.casestudy.microservice.registeruser;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan
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
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		// CustomSQLErrorCodesTranslator sqlErrorCodesTranslator = new
		// CustomSQLErrorCodesTranslator();
		// sqlErrorCodesTranslator.setDataSource(getDataSource());
		// jdbcTemplate.setExceptionTranslator(sqlErrorCodesTranslator);
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
				UserRegistrationConstants.LOCALHOSTURL);
		connectionFactory
				.setUsername(UserRegistrationConstants.RABITMQUSERNAME);
		connectionFactory
				.setPassword(UserRegistrationConstants.RABITMQPASSWORD);
		return connectionFactory;
	}

	@Bean
	public Queue simpleQueue() {
		return new Queue(UserRegistrationConstants.SIMPLE_MESSAGE_QUEUE);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate
				.setRoutingKey(UserRegistrationConstants.SIMPLE_MESSAGE_QUEUE);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public SimpleMessageListenerContainer listenerContainer() {
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory());
		listenerContainer.setQueues(simpleQueue());
		listenerContainer.setMessageConverter(jsonMessageConverter());
		// TO enable message consumer
		// listenerContainer.setMessageListener(new RabitMqMessageConsumer());
		listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return listenerContainer;
	}

}