package com.impetus.casestudy.microservice.searchuser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsersearchserviceApplicationTests {

	private RestTemplate restTemplate = new RestTemplate();
	private final static String USER_ID = "55";
	private final static String FIRSTNAME = "RabbitMQTesdtaa";
	private final static String LASTNAME = "lastName";
	private final static String EMAIL = "santoshk.jha@impetus.co.in";

	@Test
	public void testFindUserById() {
		User user = restTemplate.getForObject(
				UserSearchConstants.USER_SEARCH_BY_ID_SERVICE_URL + USER_ID,
				User.class, USER_ID);

		assertTrue(Integer.parseInt(USER_ID) == user.getId());
	}

	@Test
	public void testFindUserByFirstName() {
		User user = restTemplate.getForObject(
				UserSearchConstants.USER_SEARCH_BY_FIRSTNAME_SERVICE_URL
						+ FIRSTNAME, User.class, FIRSTNAME);

		assertTrue(FIRSTNAME.equalsIgnoreCase(user.getFirstName()));

	}

	@Test
	public void testFindUserByLastName() {
		User[] users = restTemplate.getForObject(
				UserSearchConstants.USER_SEARCH_BY_LASTNAME_SERVICE_URL
						+ LASTNAME, User[].class, LASTNAME);

		assertTrue(LASTNAME.equalsIgnoreCase(users[0].getLastName()));

	}

	@Test
	public void testFindUserByEmail() {
		User[] users = restTemplate.getForObject(
				UserSearchConstants.USER_SEARCH_BY_EMAIL_SERVICE_URL + EMAIL
						+ "/", User[].class, EMAIL);

		assertTrue(EMAIL.equalsIgnoreCase(users[0].getEmail()));

	}
}
