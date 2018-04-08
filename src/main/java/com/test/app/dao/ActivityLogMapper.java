package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.ActiveUser;

@Repository
public class ActivityLogMapper implements IActivityLogMapper {

	@Override
	public List<ActiveUser> listAllActiveUsers() {
		// TODO Auto-generated method stub
		List<ActiveUser> actvUsrLst =  new ArrayList<ActiveUser>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			actvUsrLst = session.selectList("getAllActiveUsers");
			session.commit();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		}
		finally {
			session.close();
		}
		return actvUsrLst;
	}

	@Override
	public int addUserToActiveList(ActiveUser actvusr) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.insert("addActiveUser", actvusr);
			session.commit();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		}
		finally {
			session.close();
		}
		return nora;
	}

	@Override
	public int updateUserActivityLog(ActiveUser actvusr) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("updateActiveUserInfo", actvusr);
			session.commit();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		}
		finally {
			session.close();
		}
		return nora;
	}

	@Override
	public ActiveUser getActiveUserById(String usrId) {
		// TODO Auto-generated method stub
		ActiveUser actvUsr =  new ActiveUser();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			actvUsr = session.selectOne("findActiveUserById");
			session.commit();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		}
		finally {
			session.close();
		}
		return actvUsr;
	}

	@Override
	public int deleteUserActivityLog(String usrId) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("deleteActiveUser", usrId);
			session.commit();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		}
		finally {
			session.close();
		}
		return nora;
	}
	
}
