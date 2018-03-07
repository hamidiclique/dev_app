package com.test.app.dao;

import java.util.List;

import com.test.app.entity.Role;

public interface IRoleMapper {

	public List<Role> getAllRoles();

	public Role findRoleById(String roleId);


}
