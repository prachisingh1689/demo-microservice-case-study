package com.impetus.casestudy.microservice.searchuser.service;

import java.util.List;

import com.impetus.casestudy.microservice.searchuser.User;

public interface UserSearchService {

	public abstract User findUserById(String id);

	public abstract List<User> findUserByLastName(String lastName);

	public abstract User findUserByFirstName(String firstName);

	public abstract List<User> findUserByEmail(String email);

	public abstract List<User> findAllUsers();

}