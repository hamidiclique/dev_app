package com.test.app.dto;

import java.util.List;

public class FungrpFunMapDto {
	
	private List<String> functionList;
	private String functiongrpId;
	private String functiongrpDesc;
	private String module;
	private String function;
	private String screen;
	
	public List<String> getFunctionList() {
		return functionList;
	}
	public void setFunctionList(List<String> functionList) {
		this.functionList = functionList;
	}
	public String getFunctiongrpId() {
		return functiongrpId;
	}
	public void setFunctiongrpId(String functiongrpId) {
		this.functiongrpId = functiongrpId;
	}
	public String getFunctiongrpDesc() {
		return functiongrpDesc;
	}
	public void setFunctiongrpDesc(String functiongrpDesc) {
		this.functiongrpDesc = functiongrpDesc;
	}
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
	
}
