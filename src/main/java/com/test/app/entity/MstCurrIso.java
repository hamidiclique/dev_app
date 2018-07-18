package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the MST_CURR_ISO database table.
 * 
 */
@Entity
@Table(name = "MST_CURR_ISO")
// @NamedQuery(name="MstCurrIso.findAll", query="SELECT m FROM MstCurrIso m")
public class MstCurrIso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ISO_NUM")
	private long iso_num;

	private String country;

	@Column(name = "COUNTRY_ISO")
	private String country_iso;

	private String currency;

	@Column(name = "ISO_CODE")
	private String iso_code;

	public MstCurrIso() {
	}

	public long getIso_num() {
		return iso_num;
	}

	public void setIso_num(long iso_num) {
		this.iso_num = iso_num;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry_iso() {
		return country_iso;
	}

	public void setCountry_iso(String country_iso) {
		this.country_iso = country_iso;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIso_code() {
		return iso_code;
	}

	public void setIso_code(String iso_code) {
		this.iso_code = iso_code;
	}

}