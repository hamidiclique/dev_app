package com.test.app.dao;

import java.util.List;

import com.test.app.entity.DecParam;

public interface IDecParamMapper {

	//public void addSysLoginParam(SysLoginParam slp);

	public List<DecParam> getAllSysLoginParams();

	public DecParam getSLPById(String searchId);

	public int editSysLoginParam(DecParam slp);
}
