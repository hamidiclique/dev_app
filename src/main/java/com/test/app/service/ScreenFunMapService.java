package com.test.app.service;

import java.util.List;

import com.test.app.entity.ScreenFunMap;

public interface ScreenFunMapService {
	
	public List<ScreenFunMap> getAllScrnFunMapByUser(String username);
}
