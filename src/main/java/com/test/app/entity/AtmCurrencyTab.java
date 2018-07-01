package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ATM_CURRENCY_TAB database table.
 * 
 */
@Entity
@Table(name="ATM_CURRENCY_TAB")
//@NamedQuery(name="AtmCurrencyTab.findAll", query="SELECT a FROM AtmCurrencyTab a")
public class AtmCurrencyTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name="CANISTER_TYPE")
	private BigDecimal canisterType;

	@Column(name="ISO_CURRENCY_TYPE")
	private BigDecimal isoCurrencyType;

	public AtmCurrencyTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getCanisterType() {
		return this.canisterType;
	}

	public void setCanisterType(BigDecimal canisterType) {
		this.canisterType = canisterType;
	}

	public BigDecimal getIsoCurrencyType() {
		return this.isoCurrencyType;
	}

	public void setIsoCurrencyType(BigDecimal isoCurrencyType) {
		this.isoCurrencyType = isoCurrencyType;
	}

}