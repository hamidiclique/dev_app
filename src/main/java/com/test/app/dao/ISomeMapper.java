package com.test.app.dao;

import java.util.List;

import com.test.app.dto.AtmMaster;
import com.test.app.dto.ViewAtmDto;

public interface ISomeMapper {

	public int storeNewAtmInfo(AtmMaster atmMaster);

	public List<ViewAtmDto> listAllActiveAtm();

}
