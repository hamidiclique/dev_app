package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TCP_TAB database table.
 * 
 */
@Entity
@Table(name="TCP_TAB")
//@NamedQuery(name="TcpTab.findAll", query="SELECT t FROM TcpTab t")
public class TcpTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name="DEPENDS_ON")
	private BigDecimal dependsOn;

	private String format;

	@Column(name="HEADER_LEN")
	private BigDecimal headerLen;

	private String initiator;

	@Column(name="LOCAL_PORT")
	private String localPort;

	private String machine;

	@Column(name="PING_CHECK")
	private BigDecimal pingCheck;

	@Column(name="REMOTE_ADDRESS")
	private String remoteAddress;

	@Column(name="REMOTE_PORT")
	private String remotePort;

	public TcpTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getDependsOn() {
		return this.dependsOn;
	}

	public void setDependsOn(BigDecimal dependsOn) {
		this.dependsOn = dependsOn;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public BigDecimal getHeaderLen() {
		return this.headerLen;
	}

	public void setHeaderLen(BigDecimal headerLen) {
		this.headerLen = headerLen;
	}

	public String getInitiator() {
		return this.initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getLocalPort() {
		return this.localPort;
	}

	public void setLocalPort(String localPort) {
		this.localPort = localPort;
	}

	public String getMachine() {
		return this.machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public BigDecimal getPingCheck() {
		return this.pingCheck;
	}

	public void setPingCheck(BigDecimal pingCheck) {
		this.pingCheck = pingCheck;
	}

	public String getRemoteAddress() {
		return this.remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getRemotePort() {
		return this.remotePort;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

}