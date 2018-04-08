package com.test.app.service;

import java.util.List;

import com.test.app.entity.ActiveUser;

public interface ActivityLogService {

	public List<ActiveUser> getAllActiveUsers();

	public int addActiveUser(ActiveUser actvusr);

	public int updateUserActivityLog(ActiveUser actvusr);

	public ActiveUser findActiveUserById(String usrId);
	
	public int removeUserFromActivelist(String usrId);

}
