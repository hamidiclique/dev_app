package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IAtmMasterMapper;
import com.test.app.dto.AtmMaster;
import com.test.app.dto.ViewAtmDto;

@Service
@Transactional
public class AtmMasterServiceImpl implements AtmMasterService {

	@Autowired
	IAtmMasterMapper atmMasterMapper; 

	@Override
	public int addNewAtmMachine(AtmMaster atmMaster) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = atmMasterMapper.storeNewAtmInfo(atmMaster);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public List<ViewAtmDto> getAllActiveAtm() {
		// TODO Auto-generated method stub
		List<ViewAtmDto> activeAtmlist = new ArrayList<ViewAtmDto>();
		try {
			activeAtmlist = atmMasterMapper.listAllActiveAtm();
		}
		catch(Exception ex) {
			ex.toString();
			return activeAtmlist;
		}
		return activeAtmlist;
	}

	@Override
	public AtmMaster findAtmMasterInfoByPid(String pid) {
		// TODO Auto-generated method stub
		AtmMaster atmMasterdto = new AtmMaster();
		try {
			atmMasterdto = atmMasterMapper.getAtmMasterByPid(pid);
		}
		catch(Exception ex) {
			ex.toString();
			return atmMasterdto;
		}
		return atmMasterdto;
	}

}
