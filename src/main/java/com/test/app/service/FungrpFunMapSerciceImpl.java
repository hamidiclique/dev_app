package com.test.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IFungrpFunMapper;
import com.test.app.entity.FungrpFunMap;

@Service
@Transactional
public class FungrpFunMapSerciceImpl implements FungrpFunMapSercice {

	@Autowired
	IFungrpFunMapper fungrpFunMapper;
	
	@Override
	public int removeElementsForFungrp(String functiongrpId) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = fungrpFunMapper.deleteRecordsByFungrp(functiongrpId);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public int mapCheckedFunctionsToGroup(List<FungrpFunMap> fungrpFunMapList) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = fungrpFunMapper.mapListedFunctionsToFungrp(fungrpFunMapList);			
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

}
