package com.impetus.casestudy.microservice.registeruser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationRestControllerTest {

	private RestTemplate restTemplate = new RestTemplate();
	private final static String DELETE_USER_ID = "2";
	private final static String UPDATE_USER_ID = "12";

	@Test
	public void testCreateUser() {

		restTemplate.postForObject(
				UserRegistrationConstants.USER_REGISTRATION_SERVICE_URL,
				getTestUser(), String.class);
	}

	@Test
	public void testDeleteUser() {
		restTemplate.delete(
				UserRegistrationConstants.USER_REGISTRATION_SERVICE_URL
						+ DELETE_USER_ID, DELETE_USER_ID);
	}

	@Test
	public void testUdateUser() {
		// Now create Request body with the updated Book Data.
		// Map<String, Object> requestBody = new HashMap();
		// requestBody.put("FIRST_NAME", "JunitTestFirstNameUpdate");
		// requestBody.put("LAST_NAME", "JunitTestLastName");
		// HttpHeaders requestHeaders = new HttpHeaders();
		// requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		//
		// HttpEntity<String> httpEntity = new HttpEntity<>(
		// ObjectMapper.writeValueAsString(requestBody), requestHeaders);
		//
		// restTemplate.exchange(
		// UserRegistrationConstants.USER_REGISTRATION_SERVICE_URL
		// + UPDATE_USER_ID, HttpMethod.PUT, httpEntity,
		// ResponseEntity.ok());
		//
		// restTemplate.postForObject(
		// UserRegistrationConstants.USER_REGISTRATION_SERVICE_URL
		// + UPDATE_USER_ID, UPDATE_USER_ID, getUserForUpdate(),
		// ResponseEntity.class);
	}
	public User getTestUser() {
		User user = new User();
		user.setFirstName("JunitTestFirstName");
		user.setLastName("JunitTestLastName");
		user.setAge("22");
		user.setEmail("junittest@gmail.com");
		return user;
	}
	public User getUserForUpdate() {
		User user = new User();
		user.setFirstName("JunitTestFirstNameUpdate");
		user.setLastName("JunitTestLastNameUpdate");
		user.setAge("22");
		user.setEmail("junittestupdate@gmail.com");
		return user;
	}
}
