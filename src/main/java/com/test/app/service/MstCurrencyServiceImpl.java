package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IMstCurrencyMapper;
import com.test.app.entity.MstCurrIso;

@Service
@Transactional
public class MstCurrencyServiceImpl implements MstCurrencyService {

	@Autowired
	IMstCurrencyMapper mstCurrencyMapper;
	
	@Override
	public List<MstCurrIso> getCurrencyList() {
		// TODO Auto-generated method stub
		List<MstCurrIso> currencyLst = new ArrayList<MstCurrIso>();
		try {
			currencyLst = mstCurrencyMapper.getAllCurrencies();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return currencyLst;
	}

}
