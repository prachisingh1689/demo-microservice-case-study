package com.impetus.casestudy.microservice.registeruser.dao;

import com.impetus.casestudy.microservice.registeruser.User;

public interface UserRegistrationDao {

	public abstract int create(User user);

	public abstract int update(Long id, User user);

	public abstract int delete(Long id);
}