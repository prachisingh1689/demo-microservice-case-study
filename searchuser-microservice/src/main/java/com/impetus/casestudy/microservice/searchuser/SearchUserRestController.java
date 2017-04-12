package com.impetus.casestudy.microservice.searchuser;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.impetus.casestudy.microservice.searchuser.service.UserSearchService;

@RestController
// @Path("/user")
public class SearchUserRestController {
	@Autowired
	UserSearchService userSearchService;
	protected Logger logger = Logger.getLogger(SearchUserRestController.class
			.getName());

	@GetMapping(value = "/user/id/{id}")
	public User findUserById(@PathVariable("id") String id) {
		logger.info("SearchUserRestController findUserById() invoked: " + id);
		return userSearchService.findUserById(id);
	}

	@GetMapping(value = "/user/firstName/{firstName}")
	public User findUserByFirstName(@PathVariable("firstName") String firstName) {
		logger.info("SearchUserRestController byFirstName() invoked: "
				+ firstName);
		return userSearchService.findUserByFirstName(firstName);
	}

	@GetMapping(value = "/user/lastName/{lastName}")
	public List<User> findUserByLastName(
			@PathVariable("lastName") String lastName) {
		logger.info("SearchUserRestController byLastName() invoked: "
				+ lastName);
		return userSearchService.findUserByLastName(lastName);
	}

	@GetMapping(value = "/user/email/{email}")
	public List<User> findUserByEmail(
	// @RequestParam(value = "email") String email) {
			@PathVariable("email") String email) {
		logger.info("SearchUserRestController findUserByEmail() invoked: "
				+ email);
		return userSearchService.findUserByEmail(email);
	}

	@GetMapping(produces = "application/json")
	public List<User> findAllUsers() {
		logger.info("SearchUserRestController findAllUsers() invoked..");
		return userSearchService.findAllUsers();
	}
}