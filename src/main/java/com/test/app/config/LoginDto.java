package com.test.app.config;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDto {
	
	private static final Logger logger = Logger.getLogger(LoginDto.class);
	
	@Autowired
	ServletContext servletContext;

	private String userId;
	private boolean alreadyLoggedIn;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isAlreadyLoggedIn() {
		return alreadyLoggedIn;
	}

	public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
		this.alreadyLoggedIn = alreadyLoggedIn;
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof LoginDto) && (getUserId() != null)
				? getUserId().equals(((LoginDto) other).getUserId())
				: (other == this);
	}

	@Override
	public int hashCode() {
		return (getUserId() != null) ? (this.getClass().hashCode() + getUserId().hashCode()) : super.hashCode();
	}
	
}
