package com.impetus.casestudy.microservice.registeruser.dao;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

public class CustomSQLErrorCodesTranslator
		extends
			SQLErrorCodeSQLExceptionTranslator {
	protected DataAccessException customTranslate(String task, String sql,
			SQLException sqlex) {
		System.out.println("ERROR CODE: " + sqlex.getErrorCode());
		return null;
	}
}
