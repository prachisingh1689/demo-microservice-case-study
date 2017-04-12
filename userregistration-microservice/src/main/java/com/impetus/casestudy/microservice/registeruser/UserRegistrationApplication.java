package com.impetus.casestudy.microservice.registeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.impetus.casestudy.microservice.messaging.RabitMqMessageProducer;

@SpringBootApplication
@EnableDiscoveryClient
@Import({ Configurations.class, RabitMqMessageProducer.class })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		JpaRepositoriesAutoConfiguration.class })
public class UserRegistrationApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "createuser-service");
		SpringApplication.run(UserRegistrationApplication.class, args);
	}
}
