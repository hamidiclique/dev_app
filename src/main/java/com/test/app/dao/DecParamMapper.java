package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.DecParam;

@Repository
public class DecParamMapper implements IDecParamMapper {

/*	@Override
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
	}*/

	@Override
	public List<DecParam> getAllSysLoginParams() {
		List<DecParam> slpList = new ArrayList<DecParam>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			slpList = session.selectList("getAllDecisionParams");
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
	public DecParam getSLPById(String searchId) {
		// TODO Auto-generated method stub
		DecParam slp = new DecParam();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			slp = session.selectOne("findByDecParamId", searchId);
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
	public int editSysLoginParam(DecParam slp) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("editDecisionParam", slp);
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
		return nora;
	}

}
