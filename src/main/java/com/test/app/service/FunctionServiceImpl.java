package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.entity.Function;
import com.test.app.dao.IFunctionMapper;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	IFunctionMapper funcMapper;
	
	@Override
	public List<Function> getFunctionsByUser(String username) {
		// TODO Auto-generated method stub
		List<Function> listFunction = new ArrayList<Function>();
		try {
			listFunction = funcMapper.listFunctionsByUser(username);
		}
		catch(Exception ex) {
			ex.toString();
			return listFunction;
		}
		return listFunction;
	}

	@Override
	public List<Function> getAllFunctions() {
		// TODO Auto-generated method stub
		List<Function> listFunction = new ArrayList<Function>();
		try {
			listFunction = funcMapper.listFunctions();
		}
		catch(Exception ex) {
			ex.toString();
			return listFunction;
		}
		return listFunction;
	}

	@Override
	public Function getFunctionById(String fid) {
		// TODO Auto-generated method stub
		Function function = new Function();
		try {
			function = funcMapper.getFunctionById(fid);
		}
		catch(Exception ex) {
			ex.toString();
			return function;
		}
		return function;
	}

	@Override
	public List<Function> listFunctionsByModule(String mid) {
		// TODO Auto-generated method stub
		List<Function> listFunction = new ArrayList<Function>();
		try {
			listFunction = funcMapper.getFunctionsByModule(mid);
		}
		catch(Exception ex) {
			ex.toString();
			return listFunction;
		}
		return listFunction;
	}

	@Override
	public List<Function> listFunctionsByFungrp(String pid) {
		// TODO Auto-generated method stub
		List<Function> listFunction = new ArrayList<Function>();
		try {
			listFunction = funcMapper.listFunctionsByFungrp(pid);
		}
		catch(Exception ex) {
			ex.toString();
			return listFunction;
		}
		return listFunction;
	}

	@Override
	public Function getModuleIdByFungrp(String pid) {
		// TODO Auto-generated method stub
		Function fun = new Function();
		try {
			fun = funcMapper.findModuleIdByFungrp(pid);
		}
		catch(Exception ex) {
			ex.toString();
			return fun;
		}
		return fun;
	}	

}
