package com.impetus.casestudy.microservice.registeruser;

public class UserRegistrationConstants {

	public static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
	public static final String AGE = "AGE";
	public static final String LAST_NAME = "LAST_NAME";
	public static final String FIRST_NAME = "FIRST_NAME";
	public static final String USER_ID = "USER_ID";
	public static final String SIMPLE_MESSAGE_QUEUE = "casestudy.usermanagement.rabbitmq.queue";
	public static final String LOCALHOSTURL = "localhost";
	public static final String RABITMQUSERNAME = "guest";
	public static final String RABITMQPASSWORD = "guest";
	public static final String SERVICE_RESPONSE_TYPE = "application/json";
	public static final String NOUSERFOUND = "No User found for ID ";
	public static final String INCORRECT_ID = "Either User Not Found or Incorrect Input Provided for : ";
	public static final String RBMQ_TEST_MESSAGE = "Test message from USER Service..";
	public static final String RBMQ_USER_REGISTRATION_MESSAGE = "USER registered sucessfully: ";
	public static final String RBMQ_PRODUCER_MESSAGE_INVOKE = "RabitMqMessageProducer send() invoked for sending message:";
	public static final String RBMQ_PRODUCER_MESSAGE_SENT = "RabitMqMessageProducer sent message successfully.. ";
	public static final String RBMQ_CONSUMER_MESSAGE_RECEIVED = "RabitMqMessageConsumer received message successfully: ";
	public static final String USER_REGISTRATION_SERVICE_URL = "http://localhost:2223/user/";
	public static final String USER_REGISTRATION_SERVICE_DELETE_URL = "http://localhost:2223/user/1";

}
