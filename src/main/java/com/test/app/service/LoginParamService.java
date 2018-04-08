package com.test.app.service;

import java.util.List;

import com.test.app.entity.DecParam;

public interface LoginParamService {

	//public void addNewLoginParam(DecParam slp);

	public List<DecParam> fetchAllLoginParams();

	public DecParam getLoginParamById(String searchId);

	public int updateLoginParamValue(DecParam slp);

}
