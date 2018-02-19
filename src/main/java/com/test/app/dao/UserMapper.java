package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.User;

@Repository
public class UserMapper implements IUserMapper{
	
	public void saveNewUser(User user) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.insert("insertNewUser", user);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
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
	public void updateUserInfoProcess(User user) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.update("updateUserInfo", user);
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
	
}
