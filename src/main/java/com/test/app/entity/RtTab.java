package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the RT_TAB database table.
 * 
 */
@Entity
@Table(name = "RT_TAB")
// @NamedQuery(name="RtTab.findAll", query="SELECT r FROM RtTab r")
public class RtTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	private String datax;

	private String descx;

	@Column(name = "DEV_TYPE")
	private String dev_type;

	private BigDecimal etype;

	@Column(name = "\"FOREIGN\"")
	private BigDecimal foreign;

	private BigDecimal formater;

	private BigDecimal monitor;

	private BigDecimal offln;

	private BigDecimal owner;

	private String proto;

	public RtTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getDatax() {
		return this.datax;
	}

	public void setDatax(String datax) {
		this.datax = datax;
	}

	public String getDescx() {
		return this.descx;
	}

	public void setDescx(String descx) {
		this.descx = descx;
	}

	public String getDev_type() {
		return dev_type;
	}

	public void setDev_type(String dev_type) {
		this.dev_type = dev_type;
	}

	public BigDecimal getEtype() {
		return this.etype;
	}

	public void setEtype(BigDecimal etype) {
		this.etype = etype;
	}

	public BigDecimal getForeign() {
		return this.foreign;
	}

	public void setForeign(BigDecimal foreign) {
		this.foreign = foreign;
	}

	public BigDecimal getFormater() {
		return this.formater;
	}

	public void setFormater(BigDecimal formater) {
		this.formater = formater;
	}

	public BigDecimal getMonitor() {
		return this.monitor;
	}

	public void setMonitor(BigDecimal monitor) {
		this.monitor = monitor;
	}

	public BigDecimal getOffln() {
		return this.offln;
	}

	public void setOffln(BigDecimal offln) {
		this.offln = offln;
	}

	public BigDecimal getOwner() {
		return this.owner;
	}

	public void setOwner(BigDecimal owner) {
		this.owner = owner;
	}

	public String getProto() {
		return this.proto;
	}

	public void setProto(String proto) {
		this.proto = proto;
	}

}