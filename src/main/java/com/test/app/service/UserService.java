package com.test.app.service;

import java.util.List;

import com.test.app.entity.User;

public interface UserService {

	public void storeNewUserData(User user);
	public List<User> fetchAllUsers();
	public User getUserById(String searchId);
	public void updateUserInfoProcess(User user);

}
