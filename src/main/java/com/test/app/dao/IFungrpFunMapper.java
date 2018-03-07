package com.test.app.dao;

import java.util.List;

import com.test.app.entity.FungrpFunMap;

public interface IFungrpFunMapper {

	public void deleteRecordsByFungrp(String functiongrpId);

	public void mapListedFunctionsToFungrp(List<FungrpFunMap> fungrpFunMapList);

}
