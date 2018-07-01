package com.test.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.AtmCmdTab;

@Repository
public class AtmCmdMapper implements IAtmCmdMapper {

	@Override
	public AtmCmdTab getAtmCmdDataByPid(String pid) {
		// TODO Auto-generated method stub
		AtmCmdTab atmcmd = new AtmCmdTab();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			atmcmd = session.selectOne("findAtmCmdById", pid);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return atmcmd;
	}

	@Override
	public int handleAtmCmdUpdate(AtmCmdTab atmcmd) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("updateAtmCmdByPid", atmcmd);
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
