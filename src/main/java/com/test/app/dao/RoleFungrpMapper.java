package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.RoleFungrpMap;

@Repository
public class RoleFungrpMapper implements IRoleFungrpMapper {

	@Override
	public List<RoleFungrpMap> getFungroupsByRoleId(String roleId) {
		// TODO Auto-generated method stub
		List<RoleFungrpMap> roleFungrpMapList = new ArrayList<RoleFungrpMap>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			roleFungrpMapList = session.selectList("findFungroupsByRoleId", roleId);
			session.commit();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} 
		finally {
			session.close();
		} 		
		return roleFungrpMapList;
	}

	@Override
	public List<RoleFungrpMap> getAllRoleFungroups() {
		// TODO Auto-generated method stub
		List<RoleFungrpMap> roleFungrpMapList = new ArrayList<RoleFungrpMap>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			roleFungrpMapList = session.selectList("getAllRoleFungroupMap");
			session.commit();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} 
		finally {
			session.close();
		} 		
		return roleFungrpMapList;
	}

	@Override
	public int deleteFungroupsForRole(String roleId) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.delete("deleteFungroupsByRoleId", roleId);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return nora;
	}

	@Override
	public int mapFungrpsToRole(List<RoleFungrpMap> roleFungrpMapList) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {			
			nora = session.insert("mapFungroupsToRole", roleFungrpMapList);			
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();			
		}
		return nora;
	}
	
}
