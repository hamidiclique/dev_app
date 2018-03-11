package com.test.app.dao;

import java.util.List;

import com.test.app.entity.RoleFungrpMap;

public interface IRoleFungrpMapper {

	public List<RoleFungrpMap> getFungroupsByRoleId(String roleId);

	public List<RoleFungrpMap> getAllRoleFungroups();

	public int deleteFungroupsForRole(String roleId);
	
	public int mapFungrpsToRole(List<RoleFungrpMap> roleFungrpMapList);

}
