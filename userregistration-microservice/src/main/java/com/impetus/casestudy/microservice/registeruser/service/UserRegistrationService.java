package com.impetus.casestudy.microservice.registeruser.service;

import com.impetus.casestudy.microservice.registeruser.User;

public interface UserRegistrationService {

	public abstract int create(User user);

	public abstract int update(Long id, User user);

	public abstract int delete(Long id);
}