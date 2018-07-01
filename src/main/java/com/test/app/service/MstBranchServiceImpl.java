package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IMstBranchMapper;
import com.test.app.entity.MstBranch;

@Service
@Transactional
public class MstBranchServiceImpl implements MstBranchService {

	@Autowired
	IMstBranchMapper branchMapper;

	@Override
	public List<MstBranch> getAllBranches() {
		// TODO Auto-generated method stub
		List<MstBranch> branchLst =  new ArrayList<MstBranch>();
		try {
			branchLst = branchMapper.listAllBranches();
		} catch (Exception ex) {
			ex.toString();
			return branchLst;
		}
		return branchLst;
	}

	@Override
	public MstBranch findBranchById(String bid) {
		// TODO Auto-generated method stub
		MstBranch brnch = new MstBranch();
		try {
			brnch = branchMapper.getBranchById(bid);
		} catch (Exception ex) {
			ex.toString();
			return brnch;
		}
		return brnch;
	}

}
