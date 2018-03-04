package com.test.app.service;

import java.util.List;

import com.test.app.entity.Function;

public interface FunctionService {

	public List<Function> getFunctionsByUser(String username);

	public List<Function> getAllFunctions();

}
