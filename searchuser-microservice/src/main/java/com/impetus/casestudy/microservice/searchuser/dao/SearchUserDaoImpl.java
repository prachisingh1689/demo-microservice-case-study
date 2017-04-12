package com.impetus.casestudy.microservice.searchuser.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.impetus.casestudy.microservice.searchuser.User;
import com.impetus.casestudy.microservice.searchuser.UserSearchConstants;

@Repository("searchUserDAO")
@PropertySource("classpath:sql.properties")
public class SearchUserDaoImpl implements SearchUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	HazelcastInstance hazelcastInstance;

	@Value("${searchbyid}")
	private String FINDUSERBYID_QUERY;

	@Value("${searchbyfirstname}")
	private String FINDUSERBYFIRSTNAME_QUERY;

	@Value("${searchbylastname}")
	private String FINDUSERBYLASTNAME_QUERY;

	@Value("${searchbyemail}")
	private String FINDUSERBYEMAIL_QUERY;

	@Value("${searchall}")
	private String FINDALLUSERS_QUERY;

	protected Logger logger = Logger.getLogger(SearchUserDaoImpl.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.impetus.casestudy.microservice.searchuser.SearchUserDao#findUserById
	 * (java.lang.String)
	 */
	@Override
	public User findUserById(String userId) {
		logger.info("UserDAO findUserById() invoked: " + userId);
		return findUser(FINDUSERBYID_QUERY, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.impetus.casestudy.microservice.searchuser.SearchUserDao#
	 * findUserByLastName(java.lang.String)
	 */
	@Override
	public List<User> findUserByLastName(String lastName) {
		logger.info("UserDAO findUserByLastName() invoked: " + lastName);
		return findUsers(FINDUSERBYLASTNAME_QUERY, lastName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.impetus.casestudy.microservice.searchuser.SearchUserDao#
	 * findUserByFirstName(java.lang.String)
	 */
	@Override
	public User findUserByFirstName(String firstName) {
		logger.info("UserDAO findUserByFirstName() invoked: " + firstName);
		User user = null;
		IMap<String, User> map = hazelcastInstance.getMap(firstName);
		if (map.containsKey(firstName)) {
			logger.info("UserDAO findUserByFirstName() getting user from cache: "
					+ firstName);
			user = map.get(firstName);
		} else {
			if (findUser(FINDUSERBYFIRSTNAME_QUERY, firstName) != null) {
				user = findUser(FINDUSERBYFIRSTNAME_QUERY, firstName);
				map.put(firstName, user);
			}
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.impetus.casestudy.microservice.searchuser.SearchUserDao#findUserByEmail
	 * (java.lang.String)
	 */
	@Override
	public List<User> findUserByEmail(String email) {
		logger.info("UserDAO findUserByEmail() invoked: " + email);
		return findUsers(FINDUSERBYEMAIL_QUERY, email);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.impetus.casestudy.microservice.searchuser.SearchUserDao#findAllUsers
	 * ()
	 */
	@Override
	public List<User> findAllUsers() {
		return findUsers(FINDALLUSERS_QUERY, null);
	}

	public List<User> findUsers(String query, String param) {
		List userList = null;
		List<Map<String, Object>> userMap = null;
		if (param != null) {
			userMap = jdbcTemplate.queryForList(query, new Object[]{param});
		} else {
			userMap = jdbcTemplate.queryForList(query);
		}
		if (userMap != null && !userMap.isEmpty()) {
			userList = getUserList(userMap);
		}
		return userList;
	}

	private User findUser(String query, String queryParam) {
		logger.info("UserDAO findUser() getting user from database: "
				+ queryParam);
		return (User) jdbcTemplate.queryForObject(query,
				new Object[]{queryParam}, new UserRowMapper());
	}

	private List<User> getUserList(List<Map<String, Object>> userMap) {
		List<User> userList = new ArrayList<>();
		for (Map row : userMap) {
			User user = new User();
			user.setId((Integer) (row.get(UserSearchConstants.USER_ID)));
			user.setFirstName((String) row.get(UserSearchConstants.FIRST_NAME));
			user.setLastName((String) row.get(UserSearchConstants.LAST_NAME));
			user.setAge(String.valueOf(row.get(UserSearchConstants.AGE)));
			user.setEmail((String) row.get(UserSearchConstants.EMAIL_ADDRESS));
			userList.add(user);
		}
		return userList;
	}
}