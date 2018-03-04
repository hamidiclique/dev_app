package com.test.app.dto;

import java.util.List;

public class ViewAuthDto {
	
	private boolean allowedAccess;
	private List<ButtonDto> btnList;
		
	public boolean isAllowedAccess() {
		return allowedAccess;
	}
	public void setAllowedAccess(boolean allowedAccess) {
		this.allowedAccess = allowedAccess;
	}
	public List<ButtonDto> getBtnList() {
		return btnList;
	}
	public void setBtnList(List<ButtonDto> btnList) {
		this.btnList = btnList;
	}
	
}
