package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.Role;

@Repository
public class RoleMapper implements IRoleMapper {

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		List<Role> roleList = new ArrayList<Role>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			roleList = session.selectList("listAllRoles");
			session.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return roleList;
	}

	@Override
	public Role findRoleById(String roleId) {
		// TODO Auto-generated method stub
		Role role = new Role();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			role = session.selectOne("findRoleById", roleId);
			session.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return role;
	}

}
