package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IScreenFunMapper;
import com.test.app.entity.ScreenFunMap;

@Service
@Transactional
public class ScreenFunMapServiceImpl implements ScreenFunMapService {
	
	@Autowired
	IScreenFunMapper scrFunMapper;
	
	@Override
	public List<ScreenFunMap> getAllScrnFunMapByUser(String username) {
		// TODO Auto-generated method stub
		List<ScreenFunMap> listScrnFunMap = new ArrayList<ScreenFunMap>();
		try {
			listScrnFunMap = scrFunMapper.listScrFunMapsByUser(username);
		}
		catch(Exception ex) {
			ex.toString();
			return listScrnFunMap;
		}
		return listScrnFunMap;
	}

	@Override
	public List<ScreenFunMap> getDistinctScrnFunPairByUser(String username) {
		// TODO Auto-generated method stub
		List<ScreenFunMap> listScrnFunMap = new ArrayList<ScreenFunMap>();
		try {
			listScrnFunMap = scrFunMapper.listDistinctScrFunPairByUser(username);
		}
		catch(Exception ex) {
			ex.toString();
			return listScrnFunMap;
		}
		return listScrnFunMap;
	}	

}
