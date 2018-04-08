package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IDecParamMapper;
import com.test.app.entity.DecParam;

@Service
@Transactional
public class LoginParamServiceImpl implements LoginParamService {

	@Autowired
	IDecParamMapper decParamMapper;

/*	@Override
	public void addNewLoginParam(DecParam slp) {
		// TODO Auto-generated method stub
		try {
			decParamMapper.addDecParam(slp);
		} catch (Exception ex) {
			ex.toString();
		}
	}*/

	@Override
	public List<DecParam> fetchAllLoginParams() {
		// TODO Auto-generated method stub
		List<DecParam> slpList = new ArrayList<DecParam>();
		try {
			slpList = decParamMapper.getAllSysLoginParams();
			return slpList;
		} catch (Exception ex) {
			ex.toString();
		}
		return slpList;
	}

	@Override
	public DecParam getLoginParamById(String searchId) {
		// TODO Auto-generated method stub
		DecParam slp = new DecParam();
		try {
			slp = decParamMapper.getSLPById(searchId);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return slp;
	}

	@Override
	public int updateLoginParamValue(DecParam slp) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = decParamMapper.editSysLoginParam(slp);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

}
