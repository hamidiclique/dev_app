package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the ATM_CMD_TAB database table.
 * 
 */
@Entity
@Table(name = "ATM_CMD_TAB")
// @NamedQuery(name="AtmCmdTab.findAll", query="SELECT a FROM AtmCmdTab a")
public class AtmCmdTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name = "CMD_CODE")
	private BigDecimal cmd_code;

	@Column(name = "CMD_DATETIME")
	private String cmd_datetime;

	@Column(name = "CMD_ISSUER")
	private String cmd_issuer;

	@Column(name = "CMD_PROCESSED_ON")
	private String cmd_processed_on;

	@Column(name = "CMD_STATUS")
	private BigDecimal cmd_status;

	public AtmCmdTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getCmd_code() {
		return cmd_code;
	}

	public void setCmd_code(BigDecimal cmd_code) {
		this.cmd_code = cmd_code;
	}

	public String getCmd_datetime() {
		return cmd_datetime;
	}

	public void setCmd_datetime(String cmd_datetime) {
		this.cmd_datetime = cmd_datetime;
	}

	public String getCmd_issuer() {
		return cmd_issuer;
	}

	public void setCmd_issuer(String cmd_issuer) {
		this.cmd_issuer = cmd_issuer;
	}

	public String getCmd_processed_on() {
		return cmd_processed_on;
	}

	public void setCmd_processed_on(String cmd_processed_on) {
		this.cmd_processed_on = cmd_processed_on;
	}

	public BigDecimal getCmd_status() {
		return cmd_status;
	}

	public void setCmd_status(BigDecimal cmd_status) {
		this.cmd_status = cmd_status;
	}

}