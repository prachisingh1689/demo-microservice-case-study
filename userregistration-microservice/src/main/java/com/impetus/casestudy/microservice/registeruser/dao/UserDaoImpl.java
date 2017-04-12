package com.impetus.casestudy.microservice.registeruser.dao;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.casestudy.microservice.registeruser.User;

@Repository("userDAO")
@PropertySource("classpath:sql.properties")
public class UserDaoImpl implements UserRegistrationDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${createuser}")
	private String CREATEUSER_QUERY;

	@Value("${updateuser}")
	private String UPDATEUSER_QUERY;

	@Value("${deleteuser}")
	private String DELETEUSER_QUERY;

	protected Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.impetus.casestudy.microservice.adduser.UserDao#create(com.impetus
	 * .casestudy.microservice.adduser.User)
	 */
	@Override
	public int create(User user) {
		return jdbcTemplate.update(
				CREATEUSER_QUERY,
				new Object[]{user.getFirstName(), user.getLastName(),
						user.getAge(), user.getEmail()});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.impetus.casestudy.microservice.adduser.UserDao#update(java.lang.Long,
	 * com.impetus.casestudy.microservice.adduser.User)
	 */
	@Override
	public int update(Long id, User user) {
		return jdbcTemplate.update(UPDATEUSER_QUERY, user.getFirstName(),
				user.getLastName(), Integer.parseInt(user.getAge()),
				user.getEmail(), id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.impetus.casestudy.microservice.adduser.UserDao#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long userId) {
		return jdbcTemplate.update(DELETEUSER_QUERY, userId);
	}
}