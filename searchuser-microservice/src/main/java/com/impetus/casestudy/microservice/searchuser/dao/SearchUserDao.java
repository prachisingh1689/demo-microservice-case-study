package com.impetus.casestudy.microservice.searchuser.dao;

import java.util.List;

import com.impetus.casestudy.microservice.searchuser.User;

public interface SearchUserDao {

	public abstract User findUserById(String id);

	public abstract User findUserByFirstName(String firstName);

	public abstract List<User> findUserByLastName(String lastName);

	public abstract List<User> findUserByEmail(String email);

	public abstract List<User> findAllUsers();

}