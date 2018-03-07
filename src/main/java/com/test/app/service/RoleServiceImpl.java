package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IRoleMapper;
import com.test.app.entity.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	IRoleMapper roleMapper;
	
	@Override
	public List<Role> listAllRoles() {
		// TODO Auto-generated method stub
		List<Role> listOfRoles = new ArrayList<Role>();
		try {
			listOfRoles = roleMapper.getAllRoles();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			return listOfRoles;
		}
		return listOfRoles;
	}

	@Override
	public Role getRoleById(String roleId) {
		// TODO Auto-generated method stub
		Role role = new Role();
		try {
			role = roleMapper.findRoleById(roleId);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			return role;
		}
		return role;
	}

}
