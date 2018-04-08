package com.test.app.dao;

import java.util.List;

import com.test.app.entity.ActiveUser;

public interface IActivityLogMapper {

	public List<ActiveUser> listAllActiveUsers();

	public int addUserToActiveList(ActiveUser actvusr);

	public int updateUserActivityLog(ActiveUser actvusr);

	public ActiveUser getActiveUserById(String usrId);

	public int deleteUserActivityLog(String usrId);

}
