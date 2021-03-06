package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the ECFOP_TAB database table.
 * 
 */
@Entity
@Table(name = "ECFOP_TAB")
// @NamedQuery(name="EcfopTab.findAll", query="SELECT e FROM EcfopTab e")
public class EcfopTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name = "EOP_NO")
	private BigDecimal eop_no;

	private BigDecimal eopt;

	public EcfopTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getEop_no() {
		return eop_no;
	}

	public void setEop_no(BigDecimal eop_no) {
		this.eop_no = eop_no;
	}

	public BigDecimal getEopt() {
		return this.eopt;
	}

	public void setEopt(BigDecimal eopt) {
		this.eopt = eopt;
	}

}