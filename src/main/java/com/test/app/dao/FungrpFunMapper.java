package com.test.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.FungrpFunMap;

@Repository
public class FungrpFunMapper implements IFungrpFunMapper {

	@Override
	public int deleteRecordsByFungrp(String functiongrpId) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.delete("deleteByFungrpId", functiongrpId);
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
	public int mapListedFunctionsToFungrp(List<FungrpFunMap> fungrpFunMapList) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {			
	        nora = session.insert("mapFunctionsToFunroup", fungrpFunMapList);			
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
