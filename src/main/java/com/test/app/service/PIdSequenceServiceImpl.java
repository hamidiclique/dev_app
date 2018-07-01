package com.test.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IPIdSequenceMapper;

@Service
@Transactional
public class PIdSequenceServiceImpl implements PIdSequenceService {

	@Autowired
	IPIdSequenceMapper pidSeqMapper;

	@Override
	public int getNextPIdSeqValue() {
		// TODO Auto-generated method stub
		int val = 0;
		try {
			val = pidSeqMapper.getPIdNextval();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return val;
	}

	@Override
	public int getCurrPIdSeqValue() {
		// TODO Auto-generated method stub
		int val = 0;
		try {
			val = pidSeqMapper.getCurrPIdSeqValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return val;
	}
}
