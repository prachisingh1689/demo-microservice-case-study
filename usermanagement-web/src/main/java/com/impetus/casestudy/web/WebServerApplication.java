package com.impetus.casestudy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Accounts web-server. Works as a microservice client, fetching data from the
 * Account-Service. Uses the Discovery Server (Eureka) to find the microservice.
 * 
 * @author Paul Chapman
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
// Disable component scanner
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
public class WebServerApplication {

	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String USER_SEARCH_SERVICE_URL = "http://SEARCHUSER-SERVICE";
	public static final String USER_CREATE_SERVICE_URL = "http://CREATEUSER-SERVICE";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServerApplication.class, args);
	}

	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * Note that prior to the "Brixton"
	 * 
	 * @return
	 */
	// @LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebCreateUserService userCreateService() {
		return new WebCreateUserService(USER_CREATE_SERVICE_URL);
	}

	@Bean
	public WebSearchUserService userSearchService() {
		return new WebSearchUserService(USER_SEARCH_SERVICE_URL);
	}

	@Bean
	public WebUserController accountsController() {
		return new WebUserController(userCreateService(), userSearchService());
	}

	// @Bean
	// public HomeController homeController() {
	// return new HomeController();
	// }
}
