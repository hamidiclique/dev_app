package com.test.app.dao;

import java.util.List;

import com.test.app.entity.MstBranch;

public interface IMstBranchMapper {

	List<MstBranch> listAllBranches();

	MstBranch getBranchById(String bid);

}
