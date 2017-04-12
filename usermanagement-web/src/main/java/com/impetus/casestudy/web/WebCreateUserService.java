package com.impetus.casestudy.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebCreateUserService {
	@Autowired
	// @LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebCreateUserService.class
			.getName());

	public static final String USER_CREATE_SUCESS = "SUCCESS";

	public WebCreateUserService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http")
				? serviceUrl
				: "http://" + serviceUrl;
	}

	public void createUser(User user) {
		logger.info("WebCreateUserService.createUser() invoked for " + user);
		String status = (String) restTemplate.postForObject(serviceUrl
				+ "/users/create/", user, String.class);
		if (USER_CREATE_SUCESS.equalsIgnoreCase(status)) {
			logger.info("Sending email to user.. " + user.getEmail());
			// TODO - Send email to user as per the status
		}
	}
}