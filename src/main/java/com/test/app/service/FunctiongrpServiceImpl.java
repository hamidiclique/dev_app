package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IFunctiongrpMapper;
import com.test.app.entity.Functiongrp;

@Service
@Transactional
public class FunctiongrpServiceImpl implements FunctiongrpService {

	@Autowired
	IFunctiongrpMapper fungrpMapper;
	
	@Override
	public List<Functiongrp> listFunctionGroups() {
		// TODO Auto-generated method stub
		List<Functiongrp> fungrpList = new ArrayList<Functiongrp>();
		try {
			fungrpList = fungrpMapper.listAllFunctionGroups();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return fungrpList;
	}

	@Override
	public Functiongrp getFunctionGroupById(String fungrpId) {
		// TODO Auto-generated method stub
		Functiongrp fungrp = new Functiongrp();
		try {
			fungrp = fungrpMapper.getFungrpById(fungrpId);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return fungrp;
	}

	@Override
	public int insertFunctionGroup(Functiongrp fungrp) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = fungrpMapper.addNewFunctionGroup(fungrp);			
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public int updateFunctionGroup(Functiongrp fungrp) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = fungrpMapper.updateFunctionGroup(fungrp);			
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

}
