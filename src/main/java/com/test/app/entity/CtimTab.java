package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the CTIM_TAB database table.
 * 
 */
@Entity
@Table(name = "CTIM_TAB")
// @NamedQuery(name="CtimTab.findAll", query="SELECT c FROM CtimTab c")
public class CtimTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name = "TIMR_LEN")
	private BigDecimal timr_len;

	@Column(name = "TIMR_NO")
	private BigDecimal timr_no;

	public CtimTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getTimr_len() {
		return timr_len;
	}

	public void setTimr_len(BigDecimal timr_len) {
		this.timr_len = timr_len;
	}

	public BigDecimal getTimr_no() {
		return timr_no;
	}

	public void setTimr_no(BigDecimal timr_no) {
		this.timr_no = timr_no;
	}

}