package com.impetus.casestudy.microservice.registeruser.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.impetus.casestudy.microservice.registeruser.User;
import com.impetus.casestudy.microservice.registeruser.UserRegistrationConstants;

public class UserRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(UserRegistrationConstants.USER_ID));
		user.setFirstName(rs.getString(UserRegistrationConstants.FIRST_NAME));
		user.setLastName(rs.getString(UserRegistrationConstants.LAST_NAME));
		user.setAge(rs.getString(UserRegistrationConstants.AGE));
		user.setEmail(rs.getString(UserRegistrationConstants.EMAIL_ADDRESS));
		return user;
	}
}