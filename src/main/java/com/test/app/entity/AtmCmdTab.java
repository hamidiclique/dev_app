package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ATM_CMD_TAB database table.
 * 
 */
@Entity
@Table(name="ATM_CMD_TAB")
//@NamedQuery(name="AtmCmdTab.findAll", query="SELECT a FROM AtmCmdTab a")
public class AtmCmdTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name="CMD_CODE")
	private BigDecimal cmdCode;

	@Column(name="CMD_DATETIME")
	private String cmdDatetime;

	@Column(name="CMD_ISSUER")
	private String cmdIssuer;

	@Column(name="CMD_PROCESSED_ON")
	private String cmdProcessedOn;

	@Column(name="CMD_STATUS")
	private BigDecimal cmdStatus;

	public AtmCmdTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getCmdCode() {
		return this.cmdCode;
	}

	public void setCmdCode(BigDecimal cmdCode) {
		this.cmdCode = cmdCode;
	}

	public String getCmdDatetime() {
		return this.cmdDatetime;
	}

	public void setCmdDatetime(String cmdDatetime) {
		this.cmdDatetime = cmdDatetime;
	}

	public String getCmdIssuer() {
		return this.cmdIssuer;
	}

	public void setCmdIssuer(String cmdIssuer) {
		this.cmdIssuer = cmdIssuer;
	}

	public String getCmdProcessedOn() {
		return this.cmdProcessedOn;
	}

	public void setCmdProcessedOn(String cmdProcessedOn) {
		this.cmdProcessedOn = cmdProcessedOn;
	}

	public BigDecimal getCmdStatus() {
		return this.cmdStatus;
	}

	public void setCmdStatus(BigDecimal cmdStatus) {
		this.cmdStatus = cmdStatus;
	}

}