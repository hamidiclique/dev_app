package com.test.app.dto;

public class ViewAtmDto {

	private String pid;
	private String machine;
	private String remote_address;
	private String branch_name;
	private String street;
	private String city;
	private String state;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getRemote_address() {
		return remote_address;
	}

	public void setRemote_address(String remote_address) {
		this.remote_address = remote_address;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
