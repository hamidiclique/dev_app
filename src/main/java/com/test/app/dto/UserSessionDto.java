package com.test.app.dto;

public class UserSessionDto {

	private String userId;
	private String exSessionId;
	private String sessionId;

	public UserSessionDto(String userLoggedIn, String existingSessionId, String sessionId) {
		// TODO Auto-generated constructor stub
		this.userId = userLoggedIn;
		this.exSessionId = existingSessionId;
		this.sessionId = sessionId;
	}
	
	public UserSessionDto( ) {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExSessionId() {
		return exSessionId;
	}

	public void setExSessionId(String exSessionId) {
		this.exSessionId = exSessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
