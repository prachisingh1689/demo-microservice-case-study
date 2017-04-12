package com.impetus.casestudy.microservice.registeruser;

import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.impetus.casestudy.microservice.messaging.RabitMqMessageProducer;
import com.impetus.casestudy.microservice.registeruser.service.UserRegistrationService;

@RestController
public class UserRegistrationRestController {
	@Autowired
	UserRegistrationService userRegistrationService;
	@Autowired
	RabitMqMessageProducer rabbMessageProducer;

	protected Logger logger = Logger
			.getLogger(UserRegistrationRestController.class.getName());

	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		logger.info("UserRestController createUser() invoked: " + user);
		int status = userRegistrationService.create(user);
		if (status == 1) {
			rabbMessageProducer
					.send(UserRegistrationConstants.RBMQ_USER_REGISTRATION_MESSAGE
							+ user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Long> deleteUser(@PathVariable Long id) {

		if (0 == userRegistrationService.delete(id)) {
			return new ResponseEntity(UserRegistrationConstants.NOUSERFOUND
					+ id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,
			@RequestBody User user) {

		int result = userRegistrationService.update(id, user);

		if (0 == result) {
			return new ResponseEntity(UserRegistrationConstants.INCORRECT_ID
					+ id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/user/test")
	public ResponseEntity testMessaging() {
		rabbMessageProducer.send(UserRegistrationConstants.RBMQ_TEST_MESSAGE);
		return null;
	}
}