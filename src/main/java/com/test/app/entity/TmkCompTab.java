package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TMK_COMP_TAB database table.
 * 
 */
@Entity
@Table(name="TMK_COMP_TAB")
//@NamedQuery(name="TmkCompTab.findAll", query="SELECT t FROM TmkCompTab t")
public class TmkCompTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TmkCompTabPK id;

	@Column(name="CLEAR_TMK_COMP")
	private String clearTmkComp;

	@Column(name="CLEAR_TMK_COMP_KCV")
	private String clearTmkCompKcv;

	@Column(name="ENCRYPTED_TMK_COMP")
	private String encryptedTmkComp;

	private String formed;

	public TmkCompTab() {
	}

	public TmkCompTabPK getId() {
		return this.id;
	}

	public void setId(TmkCompTabPK id) {
		this.id = id;
	}

	public String getClearTmkComp() {
		return this.clearTmkComp;
	}

	public void setClearTmkComp(String clearTmkComp) {
		this.clearTmkComp = clearTmkComp;
	}

	public String getClearTmkCompKcv() {
		return this.clearTmkCompKcv;
	}

	public void setClearTmkCompKcv(String clearTmkCompKcv) {
		this.clearTmkCompKcv = clearTmkCompKcv;
	}

	public String getEncryptedTmkComp() {
		return this.encryptedTmkComp;
	}

	public void setEncryptedTmkComp(String encryptedTmkComp) {
		this.encryptedTmkComp = encryptedTmkComp;
	}

	public String getFormed() {
		return this.formed;
	}

	public void setFormed(String formed) {
		this.formed = formed;
	}

}