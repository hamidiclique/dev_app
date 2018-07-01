package com.test.app.service;

import java.util.List;

import com.test.app.entity.MstBranch;

public interface MstBranchService {

	public List<MstBranch> getAllBranches();

	public MstBranch findBranchById(String bid);

}
