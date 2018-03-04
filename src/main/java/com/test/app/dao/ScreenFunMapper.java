package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.ScreenFunMap;

@Repository
public class ScreenFunMapper implements IScreenFunMapper {

	@Override
	public List<ScreenFunMap> listScrFunMapsByUser(String username) {
		// TODO Auto-generated method stub
		List<ScreenFunMap> scrnfunlist = new ArrayList<ScreenFunMap>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			scrnfunlist = session.selectList("getScreenFunMapsByUser", username);
			session.commit();
		} catch (Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return scrnfunlist;
	}

}
