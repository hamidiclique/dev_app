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

}
