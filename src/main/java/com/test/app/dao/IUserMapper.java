package com.test.app.dao;

import java.util.List;

import com.test.app.entity.User;

public interface IUserMapper {
	
	public void saveNewUser(User user);	
	public List<User> getAllUsers();
	public User getUserById(String searchId);
	public void updateUserInfoProcess(User user);
	public void processPasswordChange(User temp); 	
}
