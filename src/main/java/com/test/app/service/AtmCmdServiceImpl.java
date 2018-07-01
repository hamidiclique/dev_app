package com.test.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IAtmCmdMapper;
import com.test.app.entity.AtmCmdTab;

@Service
@Transactional
public class AtmCmdServiceImpl implements AtmCmdService {

	@Autowired
	IAtmCmdMapper atmCmdMapper;

	@Override
	public AtmCmdTab getAtmCmdDetailsByPid(String pid) {
		// TODO Auto-generated method stub
		AtmCmdTab atmcmd = new AtmCmdTab();
		try {
			atmcmd = atmCmdMapper.getAtmCmdDataByPid(pid);
		} catch (Exception ex) {
			ex.toString();
			return atmcmd;
		}
		return atmcmd;
	}

	@Override
	public int updateAtmCmdInfoByPid(AtmCmdTab atmcmd) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = atmCmdMapper.handleAtmCmdUpdate(atmcmd);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

}
