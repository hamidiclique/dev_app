package com.test.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.FungrpFunMap;

@Repository
public class FungrpFunMapper implements IFungrpFunMapper {

	@Override
	public void deleteRecordsByFungrp(String functiongrpId) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.delete("deleteByFungrpId", functiongrpId);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void mapListedFunctionsToFungrp(List<FungrpFunMap> fungrpFunMapList) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {			
	        session.insert("mapFunctionsToFunroup", fungrpFunMapList);			
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

}
