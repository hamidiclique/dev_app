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

	@Override
	public int editBranchInfo(BranchTab branchTab) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = branchMapper.updateExistingBranch(branchTab);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return nora;
	}

	@Override
	public int generateBranchId() {
		// TODO Auto-generated method stub
		int branchId = 0;
		try {
			branchId = branchMapper.getNextBranchId();
		} catch (Exception ex) {
			ex.toString();
			return branchId;
		}
		return branchId;
	}

	@Override
	public int addNewBranch(BranchTab branchTab) {
		// TODO Auto-generated method stub
		int nora = 0;
		try {
			nora = branchMapper.saveNewBranchInfo(branchTab);
		}
		catch(Exception ex) {
			ex.toString();
		}
		return nora;
	}

}
