package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.entity.MstCurrIso;

@Repository
public class MstCurrencyMapper implements IMstCurrencyMapper {

	@Override
	public List<MstCurrIso> getAllCurrencies() {
		// TODO Auto-generated method stub
		List<MstCurrIso> currencyMasterList = new ArrayList<MstCurrIso>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			currencyMasterList = session.selectList("getCurrencyList");
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return currencyMasterList;
	}	

}
