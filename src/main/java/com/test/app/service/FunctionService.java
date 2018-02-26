package com.test.app.service;

import java.util.List;

import com.test.app.entity.Function;
import com.test.app.entity.Module;

public interface FunctionService {

	public List<Function> getFunctionsByUser(String username);

}
