package com.test.app.service;

import java.util.List;

import com.test.app.entity.BranchTab;

public interface BranchTabService {

	public List<BranchTab> getAllBranches();

	public BranchTab findBranchById(String bid);

}
