package com.test.app.dto;

public class TerminalEvent {

	private String id;
	private String terminalID;
	private String location;
	private String device;
	private String startTime;
	private String status;
	private String reason;

	public TerminalEvent(String id, String terminalID, String location, String device, String startTime, String status,
			String reason) {
		this.id = id;
		this.terminalID = terminalID;
		this.location = location;
		this.device = device;
		this.startTime = startTime;
		this.status = status;
		this.reason = reason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTerminalID() {
		return terminalID;
	}

	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
