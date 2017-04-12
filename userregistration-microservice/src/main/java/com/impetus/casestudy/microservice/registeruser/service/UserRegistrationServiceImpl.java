package com.impetus.casestudy.microservice.registeruser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.casestudy.microservice.registeruser.User;
import com.impetus.casestudy.microservice.registeruser.dao.UserRegistrationDao;

@Service("userRegistrationService")
public class UserRegistrationServiceImpl implements UserRegistrationService {
	@Autowired
	UserRegistrationDao userRegistrationDao;

	public int create(User user) {
		return userRegistrationDao.create(user);
	}
	@Override
	public int update(Long id, User user) {
		return userRegistrationDao.update(id, user);
	}

	@Override
	public int delete(Long id) {
		return userRegistrationDao.delete(id);
	}

}