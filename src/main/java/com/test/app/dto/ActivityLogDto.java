package com.test.app.dto;

import java.util.List;

public class ActivityLogDto {

	private List<String> activeUserList;
	private String module;
	private String function;
	private String screen;

	public List<String> getActiveUserList() {
		return activeUserList;
	}

	public void setActiveUserList(List<String> activeUserList) {
		this.activeUserList = activeUserList;
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
