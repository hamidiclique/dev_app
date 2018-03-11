package com.test.app.service;

import java.util.List;

import com.test.app.entity.FungrpFunMap;

public interface FungrpFunMapSercice {

	public int removeElementsForFungrp(String functiongrpId);

	public int mapCheckedFunctionsToGroup(List<FungrpFunMap> fungrpFunMapList);

}
