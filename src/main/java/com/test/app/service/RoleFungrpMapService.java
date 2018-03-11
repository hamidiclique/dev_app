package com.test.app.service;

import java.util.List;

import com.test.app.entity.RoleFungrpMap;

public interface RoleFungrpMapService {

	public List<RoleFungrpMap> mapFungroupsByRole(String pid);
	
	public List<RoleFungrpMap> getAllRoleFungroupMap();

	public int deleteFungroupsForRole(String roleId);

	public int insertFungroupsForRole(List<RoleFungrpMap> roleFungrpMaps);
	
}
