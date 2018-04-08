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
	public int storeNewUserData(User user) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = userMapper.saveNewUser(user);
		}
		catch(Exception ex) {
			ex.toString();
		}
		return nora;
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
	public int updateUserInfoProcess(User user) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = userMapper.updateUserInfoProcess(user);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
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

	@Override
	public int deleteUserById(String pid) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = userMapper.removeUser(pid);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public int updateUserLoginFailCount(User temp) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = userMapper.updateUserLoginFailCount(temp);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public int updateUserStatus(User temp) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = userMapper.changeUserStatus(temp);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public int updateUserLastLogin(User temp) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = userMapper.setUserLastLoginTime(temp);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}
	
}
