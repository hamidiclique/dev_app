package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IUserMapper;
import com.test.app.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	IUserMapper userMapper;

	@Override
	public void storeNewUserData(User user) {
		// TODO Auto-generated method stub
		try {
			userMapper.saveNewUser(user);
		}
		catch(Exception ex) {
			ex.toString();
		}
	}

	@Override
	public List<User> fetchAllUsers() {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		try {
			userList = userMapper.getAllUsers();
			return userList;
		} 
		catch(Exception ex) {
			ex.toString();
		}
		return userList;
	}

	@Override
	public User getUserById(String searchId) {
		// TODO Auto-generated method stub
		User userSrv = new User();
		try {
			userSrv = userMapper.getUserById(searchId);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return userSrv;
	}

	@Override
	public void updateUserInfoProcess(User user) {
		// TODO Auto-generated method stub
		try {
			userMapper.updateUserInfoProcess(user);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
	}

	@Override
	public void processPasswordChange(User temp) {
		try {
			userMapper.processPasswordChange(temp);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
	}
	
}
