package com.test.app.dao;

import java.util.List;

import com.test.app.entity.FungrpFunMap;

public interface IFungrpFunMapper {

	public int deleteRecordsByFungrp(String functiongrpId);

	public int mapListedFunctionsToFungrp(List<FungrpFunMap> fungrpFunMapList);

}
