package com.test.app.dao;

import java.util.List;

import com.test.app.entity.BranchTab;

public interface IBranchTabMapper {

	public List<BranchTab> listAllBranches();

	public BranchTab getBranchById(String bid);

	public int updateExistingBranch(BranchTab branchTab);

	public int getNextBranchId();

	public int saveNewBranchInfo(BranchTab branchTab);

}
