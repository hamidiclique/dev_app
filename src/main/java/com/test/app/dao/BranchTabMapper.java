package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.BranchTab;

@Repository
public class BranchTabMapper implements IBranchTabMapper {

	@Override
	public List<BranchTab> listAllBranches() {
		// TODO Auto-generated method stub
		List<BranchTab> branchLst = new ArrayList<BranchTab>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			branchLst = session.selectList("getAllBranches");
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return branchLst;
	}

	@Override
	public BranchTab getBranchById(String bid) {
		// TODO Auto-generated method stub
		BranchTab brnch = new BranchTab();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			brnch = session.selectOne("findBranchById", bid);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return brnch;
	}

	@Override
	public int updateExistingBranch(BranchTab branchTab) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.update("updateBranchById", branchTab);
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
	public int getNextBranchId() {
		// TODO Auto-generated method stub
		int val = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			val = session.selectOne("getNextBranchId");
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			session.rollback();
		} finally {
			session.close();
		}
		return val;
	}

	@Override
	public int saveNewBranchInfo(BranchTab branchTab) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = session.insert("insertNewBranch", branchTab);
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
