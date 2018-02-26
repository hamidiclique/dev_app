package com.test.app.service;

import java.util.List;

import com.test.app.entity.SysLoginParam;

public interface LoginParamService {
	
	public void addNewLoginParam(SysLoginParam slp);
	public List<SysLoginParam> fetchAllLoginParams();
	public SysLoginParam getLoginParamById(String searchId);
	public void updateLoginParamValue(SysLoginParam slp);

}
