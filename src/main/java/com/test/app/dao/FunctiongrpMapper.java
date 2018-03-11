package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.Functiongrp;

@Repository
public class FunctiongrpMapper implements IFunctiongrpMapper {

	@Override
	public List<Functiongrp> listAllFunctionGroups() {
		// TODO Auto-generated method stub
		List<Functiongrp> fungrpList = new ArrayList<Functiongrp>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {			
			fungrpList = session.selectList("getAllFunctionGroups"); 
		} catch (Exception e) {
			e.toString();
		}
		return fungrpList;
	}

	@Override
	public Functiongrp getFungrpById(String fungrpId) {
		// TODO Auto-generated method stub
		Functiongrp fungrp = new Functiongrp();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			fungrp = session.selectOne("findFunctionGroupById", fungrpId);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return fungrp;
	}

	@Override
	public int addNewFunctionGroup(Functiongrp fungrp) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.insert("insertFunctionGroup", fungrp);
			session.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return nora;
	}

	@Override
	public int updateFunctionGroup(Functiongrp fungrp) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("updateFunctionGroup", fungrp);
			session.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return nora;
	}
	
}
