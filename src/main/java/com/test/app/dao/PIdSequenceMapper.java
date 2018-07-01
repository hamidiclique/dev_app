package com.test.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;

@Repository
public class PIdSequenceMapper implements IPIdSequenceMapper {

	@Override
	public int getPIdNextval() {
		// TODO Auto-generated method stub
		int val = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			val = session.selectOne("getPIdNextval");
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
	public int getCurrPIdSeqValue() {
		// TODO Auto-generated method stub
		int val = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			val = session.selectOne("getPIdCurrval");
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
