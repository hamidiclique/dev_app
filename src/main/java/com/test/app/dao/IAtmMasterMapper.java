package com.test.app.dao;

import java.util.List;

import com.test.app.dto.AtmMaster;
import com.test.app.dto.ViewAtmDto;

public interface IAtmMasterMapper {

	public int storeNewAtmInfo(AtmMaster atmMaster);

	public List<ViewAtmDto> listAllActiveAtm();

	public AtmMaster getAtmMasterByPid(String pid);

}
