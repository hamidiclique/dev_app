package com.test.app.service;

import com.test.app.entity.AtmCmdTab;

public interface AtmCmdService {

	public AtmCmdTab getAtmCmdDetailsByPid(String pid);

	public int updateAtmCmdInfoByPid(AtmCmdTab atmcmd);

}
