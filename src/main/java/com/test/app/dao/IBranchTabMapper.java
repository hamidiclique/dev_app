package com.test.app.dao;

import java.util.List;

import com.test.app.entity.BranchTab;

public interface IBranchTabMapper {

	List<BranchTab> listAllBranches();

	BranchTab getBranchById(String bid);

}
