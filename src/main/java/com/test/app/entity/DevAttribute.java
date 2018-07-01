package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DEV_ATTRIBUTES database table.
 * 
 */
@Entity
@Table(name="DEV_ATTRIBUTES")
//@NamedQuery(name="DevAttribute.findAll", query="SELECT d FROM DevAttribute d")
public class DevAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	private BigDecimal attribute;

	public DevAttribute() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getAttribute() {
		return this.attribute;
	}

	public void setAttribute(BigDecimal attribute) {
		this.attribute = attribute;
	}

}