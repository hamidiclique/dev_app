package com.test.app.dao;

import com.test.app.entity.AtmCmdTab;

public interface IAtmCmdMapper {

	public AtmCmdTab getAtmCmdDataByPid(String pid);

	public int handleAtmCmdUpdate(AtmCmdTab atmcmd);

}
