package com.test.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;

@Repository
public class TmkCompSequenceMapper implements ITmkCompSequenceMapper {

	@Override
	public int getTmkCompNextval() {
		// TODO Auto-generated method stub
		int val = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			val = session.selectOne("getTmkCompNextval");
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return val;
	}

	@Override
	public int getCurrTmkCompSeqValue() {
		// TODO Auto-generated method stub
		int val = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			val = session.selectOne("getTmkCompCurrval");
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return val;
	}

}
