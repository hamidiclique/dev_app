package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the ATM_CURRENCY_TAB database table.
 * 
 */
@Entity
@Table(name = "ATM_CURRENCY_TAB")
// @NamedQuery(name="AtmCurrencyTab.findAll", query="SELECT a FROM
// AtmCurrencyTab a")
public class AtmCurrencyTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name = "CANISTER_TYPE")
	private BigDecimal canister_type;

	@Column(name = "ISO_CURRENCY_TYPE")
	private BigDecimal iso_currency_type;

	public AtmCurrencyTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getCanister_type() {
		return canister_type;
	}

	public void setCanister_type(BigDecimal canister_type) {
		this.canister_type = canister_type;
	}

	public BigDecimal getIso_currency_type() {
		return iso_currency_type;
	}

	public void setIso_currency_type(BigDecimal iso_currency_type) {
		this.iso_currency_type = iso_currency_type;
	}

}