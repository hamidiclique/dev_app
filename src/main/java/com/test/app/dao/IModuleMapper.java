package com.test.app.dao;

import java.util.List;

import com.test.app.entity.Module;

public interface IModuleMapper {
	
	public List<Module> listModulesByUser(String username);

	public List<Module> listModules();

	public Module getModuleById(String moduleId);
	
}
