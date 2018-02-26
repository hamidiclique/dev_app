package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.SysLoginParam;

@Repository
public class SysloginParamMapper implements ISysloginParamMapper {

	@Override
	public void addSysLoginParam(SysLoginParam slp) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.insert("addSysLoginParam", slp);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public List<SysLoginParam> getAllSysLoginParams() {
		List<SysLoginParam> slpList = new ArrayList<SysLoginParam>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			slpList = session.selectList("getAllSysLoginParams");
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return slpList;
	}

	@Override
	public SysLoginParam getSLPById(String searchId) {
		// TODO Auto-generated method stub
		SysLoginParam slp = new SysLoginParam();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			slp = session.selectOne("findByParamId", searchId);
			session.commit();
		} catch(Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}		
		return slp;
	}

	@Override
	public void editSysLoginParam(SysLoginParam slp) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.update("editSysLoginParam", slp);
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
	}

}
