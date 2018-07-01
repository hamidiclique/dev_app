package com.test.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.ITmkCompSequenceMapper;

@Service
@Transactional
public class TmkCompSequenceServiceImpl implements TmkCompSequenceService {

	@Autowired
	ITmkCompSequenceMapper tmkCompSeqMapper;

	@Override
	public int getNextTmkCompSeqValue() {
		// TODO Auto-generated method stub
		int val = 0;
		try {
			val = tmkCompSeqMapper.getTmkCompNextval();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return val;
	}

	@Override
	public int getCurrTmkCompSeqValue() {
		// TODO Auto-generated method stub
		int val = 0;
		try {
			val = tmkCompSeqMapper.getCurrTmkCompSeqValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return val;
	}
}
