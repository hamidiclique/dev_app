package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.User;

@Repository
public class UserMapper implements IUserMapper {
	
	@Override
	public int saveNewUser(User user) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.insert("insertNewUser", user);
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
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			userList = session.selectList("getAllUsers");
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return userList;
	}
	
	@Override
	public User getUserById(String searchId) {
		// TODO Auto-generated method stub
		User userMpr = new User();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			userMpr = session.selectOne("findUserById", searchId);
			session.commit();
		} catch(Exception ex) {
			ex.toString();
			session.rollback();
		} finally {
			session.close();
		}		
		return userMpr;
	}
	
	@Override
	public int updateUserInfoProcess(User user) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("updateUserInfo", user);
			session.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		}
		finally {
			session.close();
		}
		return nora;
	}

	@Override
	public void processPasswordChange(User temp) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.update("updateUserPasswordInfo", temp);
			session.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		}
		finally {
			session.close();
		}
	}

	@Override
	public int removeUser(String userId) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.delete("deleteUser", userId);
			session.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		}
		finally {
			session.close();
		}
		return nora;
	}

	@Override
	public int updateUserLoginFailCount(User temp) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("setLoginFailCount", temp);
			session.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		}
		finally {
			session.close();
		}
		return nora;
	}

	@Override
	public int changeUserStatus(User temp) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("changeUserStatus", temp);
			session.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		}
		finally {
			session.close();
		}
		return nora;
	}

	@Override
	public int setUserLastLoginTime(User temp) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("updateUserLastLogin", temp);
			session.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
			e.printStackTrace();
			session.rollback();
		}
		finally {
			session.close();
		}
		return nora;
	}
	
}
