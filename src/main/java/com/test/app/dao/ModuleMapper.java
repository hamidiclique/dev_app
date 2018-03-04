package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.Module;

@Repository
public class ModuleMapper implements IModuleMapper {

	@Override
	public List<Module> listModulesByUser(String username) {
		// TODO Auto-generated method stub
		List<Module> modlist = new ArrayList<Module>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			modlist = session.selectList("getModulesByUser", username);
			session.commit();
		} catch (Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return modlist;
	}

	@Override
	public List<Module> listModules() {
		// TODO Auto-generated method stub
		List<Module> modlist = new ArrayList<Module>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			modlist = session.selectList("getAllModules");
			session.commit();
		} catch (Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return modlist;
	}

	@Override
	public Module getModuleById(String moduleId) {
		// TODO Auto-generated method stub
		Module module = new Module();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			module = session.selectOne("getModuleById", moduleId);
			session.commit();
		} catch (Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return module;
	}

}
