package com.test.app.service;

import java.util.List;

import com.test.app.dto.AtmMaster;
import com.test.app.dto.ViewAtmDto;

public interface SomeService {
	
	public int addNewAtmMachine(AtmMaster atmMaster);
	
	public List<ViewAtmDto> getAllActiveAtm();
	
}
