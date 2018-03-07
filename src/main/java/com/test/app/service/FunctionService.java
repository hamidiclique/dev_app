package com.test.app.service;

import java.util.List;

import com.test.app.entity.Function;

public interface FunctionService {

	public List<Function> getFunctionsByUser(String username);

	public List<Function> getAllFunctions();

	public Function getFunctionById(String fid);

	public List<Function> listFunctionsByModule(String mid);

	public List<Function> listFunctionsByFungrp(String pid);
	
	public Function getModuleIdByFungrp(String pid);

}
