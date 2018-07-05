package com.test.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.dao.IBranchTabMapper;
import com.test.app.entity.BranchTab;

@Service
@Transactional
public class BranchTabServiceImpl implements BranchTabService {

	@Autowired
	IBranchTabMapper branchMapper;

	@Override
	public List<BranchTab> getAllBranches() {
		// TODO Auto-generated method stub
		List<BranchTab> branchLst =  new ArrayList<BranchTab>();
		try {
			branchLst = branchMapper.listAllBranches();
		} catch (Exception ex) {
			ex.toString();
			return branchLst;
		}
		return branchLst;
	}

	@Override
	public BranchTab findBranchById(String bid) {
		// TODO Auto-generated method stub
		BranchTab brnch = new BranchTab();
		try {
			brnch = branchMapper.getBranchById(bid);
		} catch (Exception ex) {
			ex.toString();
			return brnch;
		}
		return brnch;
	}

}
