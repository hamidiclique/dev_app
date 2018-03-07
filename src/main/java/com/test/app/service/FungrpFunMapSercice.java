package com.test.app.service;

import java.util.List;

import com.test.app.entity.FungrpFunMap;

public interface FungrpFunMapSercice {

	public void removeElementsForFungrp(String functiongrpId);

	public void mapCheckedFunctionsToGroup(List<FungrpFunMap> fungrpFunMapList);

}
