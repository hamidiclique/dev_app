package com.test.app.service;

import java.util.List;

import com.test.app.entity.Module;

public interface ModuleService {

	public List<Module> getModulesByUser(String username);

	public List<Module> getAllModules();

	public Module getModuleById(String mid);

}
