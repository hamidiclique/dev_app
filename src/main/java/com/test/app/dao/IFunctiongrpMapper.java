package com.test.app.dao;

import java.util.List;

import com.test.app.entity.Functiongrp;

public interface IFunctiongrpMapper {

	public List<Functiongrp> listAllFunctionGroups();

	public Functiongrp getFungrpById(String fungrpId);

}
