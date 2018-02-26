package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.ISysloginParamMapper;
import com.test.app.entity.SysLoginParam;

@Service
@Transactional
public class LoginParamServiceImpl implements LoginParamService {

	@Autowired
	ISysloginParamMapper sysLoginParam;

	@Override
	public void addNewLoginParam(SysLoginParam slp) {
		// TODO Auto-generated method stub
		try {
			sysLoginParam.addSysLoginParam(slp);
		} catch (Exception ex) {
			ex.toString();
		}
	}

	@Override
	public List<SysLoginParam> fetchAllLoginParams() {
		// TODO Auto-generated method stub
		List<SysLoginParam> slpList = new ArrayList<SysLoginParam>();
		try {
			slpList = sysLoginParam.getAllSysLoginParams();
			return slpList;
		} catch (Exception ex) {
			ex.toString();
		}
		return slpList;
	}

	@Override
	public SysLoginParam getLoginParamById(String searchId) {
		// TODO Auto-generated method stub
		SysLoginParam slp = new SysLoginParam();
		try {
			slp = sysLoginParam.getSLPById(searchId);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return slp;
	}

	@Override
	public void updateLoginParamValue(SysLoginParam slp) {
		// TODO Auto-generated method stub
		try {
			sysLoginParam.editSysLoginParam(slp);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
	}

}
