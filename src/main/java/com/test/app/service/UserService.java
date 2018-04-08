package com.test.app.service;

import java.util.List;

import com.test.app.entity.User;

public interface UserService {

	public int storeNewUserData(User user);

	public List<User> fetchAllUsers();

	public User getUserById(String searchId);

	public int updateUserInfoProcess(User user);

	public void processPasswordChange(User temp);

	public int deleteUserById(String pid);

	public int updateUserLoginFailCount(User temp);

	public int updateUserStatus(User temp);

	public int updateUserLastLogin(User temp);

}
