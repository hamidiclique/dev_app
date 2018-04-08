package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IActivityLogMapper;
import com.test.app.entity.ActiveUser;

@Service
@Transactional
public class ActivityLogServiceImpl implements ActivityLogService {

	@Autowired
	IActivityLogMapper activityLogMapper;
	
	@Override
	public List<ActiveUser> getAllActiveUsers() {
		// TODO Auto-generated method stub
		List<ActiveUser> resList = new ArrayList<ActiveUser>();
		try {
			resList = activityLogMapper.listAllActiveUsers();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return resList;
	}

	@Override
	public int addActiveUser(ActiveUser actvusr) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = activityLogMapper.addUserToActiveList(actvusr);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public int updateUserActivityLog(ActiveUser actvusr) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = activityLogMapper.updateUserActivityLog(actvusr);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public ActiveUser findActiveUserById(String usrId) {
		// TODO Auto-generated method stub
		ActiveUser actvusr = new ActiveUser();
		try {
			actvusr = activityLogMapper.getActiveUserById(usrId);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return actvusr;
	}

	@Override
	public int removeUserFromActivelist(String usrId) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = activityLogMapper.deleteUserActivityLog(usrId);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}	

}
