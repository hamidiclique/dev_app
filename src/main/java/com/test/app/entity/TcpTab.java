package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the TCP_TAB database table.
 * 
 */
@Entity
@Table(name = "TCP_TAB")
// @NamedQuery(name="TcpTab.findAll", query="SELECT t FROM TcpTab t")
public class TcpTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name = "DEPENDS_ON")
	private BigDecimal depends_on;

	private String format;

	@Column(name = "HEADER_LEN")
	private BigDecimal header_len;

	private String initiator;

	@Column(name = "LOCAL_PORT")
	private String local_port;

	private String machine;

	@Column(name = "PING_CHECK")
	private BigDecimal ping_check;

	@Column(name = "REMOTE_ADDRESS")
	private String remote_address;

	@Column(name = "REMOTE_PORT")
	private String remote_port;

	public TcpTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public BigDecimal getDepends_on() {
		return depends_on;
	}

	public void setDepends_on(BigDecimal depends_on) {
		this.depends_on = depends_on;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public BigDecimal getHeader_len() {
		return header_len;
	}

	public void setHeader_len(BigDecimal header_len) {
		this.header_len = header_len;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getLocal_port() {
		return local_port;
	}

	public void setLocal_port(String local_port) {
		this.local_port = local_port;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public BigDecimal getPing_check() {
		return ping_check;
	}

	public void setPing_check(BigDecimal ping_check) {
		this.ping_check = ping_check;
	}

	public String getRemote_address() {
		return remote_address;
	}

	public void setRemote_address(String remote_address) {
		this.remote_address = remote_address;
	}

	public String getRemote_port() {
		return remote_port;
	}

	public void setRemote_port(String remote_port) {
		this.remote_port = remote_port;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

}