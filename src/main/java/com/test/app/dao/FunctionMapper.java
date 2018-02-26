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
		} catch(Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}		
		return funlist;
	}

}
