package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TMK_COMP_TAB database table.
 * 
 */
@Entity
@Table(name = "TMK_COMP_TAB")
// @NamedQuery(name="TmkCompTab.findAll", query="SELECT t FROM TmkCompTab t")
public class TmkCompTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TmkCompTabPK id;

	@Column(name = "CLEAR_TMK_COMP")
	private String clear_tmk_comp;

	@Column(name = "CLEAR_TMK_COMP_KCV")
	private String clear_tmk_comp_kcv;

	@Column(name = "ENCRYPTED_TMK_COMP")
	private String encrypted_tmk_comp;

	private String formed;

	public TmkCompTabPK getId() {
		return id;
	}

	public void setId(TmkCompTabPK id) {
		this.id = id;
	}

	public String getClear_tmk_comp() {
		return clear_tmk_comp;
	}

	public void setClear_tmk_comp(String clear_tmk_comp) {
		this.clear_tmk_comp = clear_tmk_comp;
	}

	public String getClear_tmk_comp_kcv() {
		return clear_tmk_comp_kcv;
	}

	public void setClear_tmk_comp_kcv(String clear_tmk_comp_kcv) {
		this.clear_tmk_comp_kcv = clear_tmk_comp_kcv;
	}

	public String getEncrypted_tmk_comp() {
		return encrypted_tmk_comp;
	}

	public void setEncrypted_tmk_comp(String encrypted_tmk_comp) {
		this.encrypted_tmk_comp = encrypted_tmk_comp;
	}

	public String getFormed() {
		return formed;
	}

	public void setFormed(String formed) {
		this.formed = formed;
	}

	public TmkCompTab() {
	}

}