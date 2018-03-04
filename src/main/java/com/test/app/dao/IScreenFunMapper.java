package com.test.app.dao;

import java.util.List;

import com.test.app.entity.ScreenFunMap;

public interface IScreenFunMapper {

	List<ScreenFunMap> listScrFunMapsByUser(String username);

}
