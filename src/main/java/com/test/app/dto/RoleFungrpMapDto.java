package com.test.app.dto;

import java.util.List;

public class RoleFungrpMapDto {

	private List<String> fungrpList;
	private List<String> leftoutFungrpList;
	private String roleId;
	private String roleDesc;
	private String module;
	private String function;
	private String screen;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public List<String> getFungrpList() {
		return fungrpList;
	}

	public void setFungrpList(List<String> fungrpList) {
		this.fungrpList = fungrpList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<String> getLeftoutFungrpList() {
		return leftoutFungrpList;
	}

	public void setLeftoutFungrpList(List<String> leftoutFungrpList) {
		this.leftoutFungrpList = leftoutFungrpList;
	}
	
}
