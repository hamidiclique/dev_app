package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.Function;

@Repository
public class FunctionMapper implements IFunctionMapper {

	@Override
	public List<Function> listFunctionsByUser(String username) {
		// TODO Auto-generated method stub
		List<Function> funlist = new ArrayList<Function>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			funlist = session.selectList("getFunctionsByUser", username);
			session.commit();
		} catch (Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return funlist;
	}

	@Override
	public List<Function> listFunctions() {
		// TODO Auto-generated method stub
		List<Function> funlist = new ArrayList<Function>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			funlist = session.selectList("getAllFunctions");
			session.commit();
		} catch (Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return funlist;
	}

	@Override
	public Function getFunctionById(String fid) {
		// TODO Auto-generated method stub
		Function function = new Function();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			function = session.selectOne("findFunctionById", fid);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}		
		return function;
	}

	@Override
	public List<Function> getFunctionsByModule(String mid) {
		// TODO Auto-generated method stub
		List<Function> funlist = new ArrayList<Function>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			funlist = session.selectList("getFunctionsByModule", mid);
			session.commit();
		} catch (Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return funlist;
	}

	@Override
	public List<Function> listFunctionsByFungrp(String pid) {
		// TODO Auto-generated method stub
		List<Function> funlist = new ArrayList<Function>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			funlist = session.selectList("getFunctionsByFungrp", pid);
			session.commit();
		} catch (Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return funlist;
	}

	@Override
	public Function findModuleIdByFungrp(String pid) {
		// TODO Auto-generated method stub
		Function fun = new Function();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			fun = session.selectOne("getModuleByFungrp", pid);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return fun;
	}

}
