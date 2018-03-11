package com.test.app.dao;

import java.util.List;

import com.test.app.entity.User;

public interface IUserMapper {

	public int saveNewUser(User user);

	public List<User> getAllUsers();

	public User getUserById(String searchId);

	public int updateUserInfoProcess(User user);

	public void processPasswordChange(User temp);

	public int removeUser(String userId);
	
}
