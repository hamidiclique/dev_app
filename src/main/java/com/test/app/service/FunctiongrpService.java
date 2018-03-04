package com.test.app.service;

import java.util.List;

import com.test.app.entity.Functiongrp;

public interface FunctiongrpService {

	public List<Functiongrp> listFunctionGroups();
	public Functiongrp getFunctionGroupById(String fungrpId); 

}
