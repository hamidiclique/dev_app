package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.entity.Module;
import com.test.app.dao.IModuleMapper;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	IModuleMapper moduleMapper;

	@Override
	public List<Module> getModulesByUser(String username) {
		// TODO Auto-generated method stub
		List<Module> listModule = new ArrayList<Module>();
		try {
			listModule = moduleMapper.listModulesByUser(username);
		} catch (Exception ex) {
			ex.toString();
			return listModule;
		}
		return listModule;
	}

	@Override
	public List<Module> getAllModules() {
		// TODO Auto-generated method stub
		List<Module> listModule = new ArrayList<Module>();
		try {
			listModule = moduleMapper.listModules();
		} catch (Exception ex) {
			ex.toString();
			return listModule;
		}
		return listModule;
	}

	@Override
	public Module getModuleById(String mid) {
		// TODO Auto-generated method stub
		Module module = new Module();
		try {
			module = moduleMapper.getModuleById(mid);
		} catch (Exception ex) {
			ex.toString();
			return module;
		}
		return module;
	}

}
