package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MST_DENOM_VALUE database table.
 * 
 */
@Entity
@Table(name="MST_DENOM_VALUE")
//@NamedQuery(name="MstDenomValue.findAll", query="SELECT m FROM MstDenomValue m")
public class MstDenomValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"VALUE\"")
	private BigDecimal value;

	public MstDenomValue() {
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}