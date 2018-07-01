package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CTIM_TAB database table.
 * 
 */
@Entity
@Table(name="CTIM_TAB")
//@NamedQuery(name="CtimTab.findAll", query="SELECT c FROM CtimTab c")
public class CtimTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name="TIMR_LEN")
	private BigDecimal timrLen;

	@Column(name="TIMR_NO")
	private BigDecimal timrNo;

	public CtimTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getTimrLen() {
		return this.timrLen;
	}

	public void setTimrLen(BigDecimal timrLen) {
		this.timrLen = timrLen;
	}

	public BigDecimal getTimrNo() {
		return this.timrNo;
	}

	public void setTimrNo(BigDecimal timrNo) {
		this.timrNo = timrNo;
	}

}