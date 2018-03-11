package com.test.app.service;

import java.util.List;

import com.test.app.entity.Role;

public interface RoleService {

	public List<Role> listAllRoles();
	
	public Role getRoleById(String roleId);

	public int addNewRole(Role role);

	public int updateRoleById(Role role);
	
}
