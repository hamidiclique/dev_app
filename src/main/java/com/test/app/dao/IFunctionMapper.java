package com.test.app.dao;

import java.util.List;

import com.test.app.entity.Function;

public interface IFunctionMapper {

	public List<Function> listFunctionsByUser(String username);

	public List<Function> listFunctions();
	
}
