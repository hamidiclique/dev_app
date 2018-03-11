package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IRoleFungrpMapper;
import com.test.app.entity.RoleFungrpMap;

@Service
@Transactional
public class RoleFungrpMapServiceImpl implements RoleFungrpMapService{

	@Autowired
	IRoleFungrpMapper roleFungrpMapper;
	
	@Override
	public List<RoleFungrpMap> mapFungroupsByRole(String pid) {
		// TODO Auto-generated method stub
		List<RoleFungrpMap> roleFungrpMapList = new ArrayList<RoleFungrpMap>();
		try {
			roleFungrpMapList = roleFungrpMapper.getFungroupsByRoleId(pid);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			return roleFungrpMapList;
		}
		return roleFungrpMapList;
	}

	@Override
	public List<RoleFungrpMap> getAllRoleFungroupMap() {
		// TODO Auto-generated method stub
		List<RoleFungrpMap> roleFungrpMapList = new ArrayList<RoleFungrpMap>();
		try {
			roleFungrpMapList = roleFungrpMapper.getAllRoleFungroups();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			return roleFungrpMapList;
		}
		return roleFungrpMapList;
	}

	@Override
	public int deleteFungroupsForRole(String roleId) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = roleFungrpMapper.deleteFungroupsForRole(roleId);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public int insertFungroupsForRole(List<RoleFungrpMap> roleFungrpMaps) {
		// TODO Auto-generated method stub
		int retval = 0;
		try {
			retval = roleFungrpMapper.mapFungrpsToRole(roleFungrpMaps);
			return retval;
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			return retval;
		}
	}

}
