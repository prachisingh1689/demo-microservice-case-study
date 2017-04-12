package com.impetus.casestudy.microservice.searchuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.casestudy.microservice.searchuser.User;
import com.impetus.casestudy.microservice.searchuser.dao.SearchUserDao;

@Service("userSearchService")
public class UserSearchServiceImpl implements UserSearchService {

	@Autowired
	private SearchUserDao searchUserDao;

	@Override
	public User findUserById(String id) {
		return searchUserDao.findUserById(id);
	}

	@Override
	public List<User> findUserByLastName(String lastName) {
		return searchUserDao.findUserByLastName(lastName);
	}
	@Override
	public User findUserByFirstName(String firstName) {
		return searchUserDao.findUserByFirstName(firstName);
	}

	@Override
	public List<User> findUserByEmail(String email) {
		return searchUserDao.findUserByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {
		return searchUserDao.findAllUsers();
	}
}