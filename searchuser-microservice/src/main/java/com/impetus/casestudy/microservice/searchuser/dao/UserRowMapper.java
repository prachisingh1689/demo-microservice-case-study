package com.impetus.casestudy.microservice.searchuser.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.impetus.casestudy.microservice.searchuser.User;
import com.impetus.casestudy.microservice.searchuser.UserSearchConstants;

public class UserRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(UserSearchConstants.USER_ID));
		user.setFirstName(rs.getString(UserSearchConstants.FIRST_NAME));
		user.setLastName(rs.getString(UserSearchConstants.LAST_NAME));
		user.setAge(rs.getString(UserSearchConstants.AGE));
		user.setEmail(rs.getString(UserSearchConstants.EMAIL_ADDRESS));
		return user;
	}
}