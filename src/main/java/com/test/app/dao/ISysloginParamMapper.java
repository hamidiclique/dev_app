package com.test.app.dao;

import java.util.List;

import com.test.app.entity.SysLoginParam;

public interface ISysloginParamMapper {
	
	public void addSysLoginParam(SysLoginParam slp);
	public List<SysLoginParam> getAllSysLoginParams();
	public SysLoginParam getSLPById(String searchId);
	public void editSysLoginParam(SysLoginParam slp);
}
