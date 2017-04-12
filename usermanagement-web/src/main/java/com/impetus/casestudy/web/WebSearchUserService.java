package com.impetus.casestudy.web;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebSearchUserService {

	@Autowired
	// @LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebSearchUserService.class
			.getName());

	public WebSearchUserService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http")
				? serviceUrl
				: "http://" + serviceUrl;
	}

	public User findByEmail(String email) {
		logger.info("WebCreateUserService.findByEmail() invoked for " + email);
		return restTemplate.getForObject(serviceUrl + "/users/find/{email}",
				User.class, email);
	}

	public User findByFirstName(String firstName) {
		logger.info("WebCreateUserService.findByFirstName() invoked for "
				+ firstName);
		return restTemplate.getForObject(serviceUrl
				+ "/users/firstname/{firstName}", User.class, firstName);
	}

	public List<User> findAllUsers() {
		logger.info("WebCreateUserService.findAllUsers invoked");
		User[] user = restTemplate.getForObject(serviceUrl + "/users/find/",
				User[].class);
		return Arrays.asList(user);
	}
}