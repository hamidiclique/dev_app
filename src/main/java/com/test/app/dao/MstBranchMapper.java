package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.MstBranch;

@Repository
public class MstBranchMapper implements IMstBranchMapper {

	@Override
	public List<MstBranch> listAllBranches() {
		// TODO Auto-generated method stub
		List<MstBranch> branchLst = new ArrayList<MstBranch>();
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
	public MstBranch getBranchById(String bid) {
		// TODO Auto-generated method stub
		MstBranch brnch = new MstBranch();
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

}
