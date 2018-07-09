package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TMK_REQ_TAB database table.
 * 
 */
@Entity
@Table(name = "TMK_REQ_TAB")
// @NamedQuery(name="TmkReqTab.findAll", query="SELECT t FROM TmkReqTab t")
public class TmkReqTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long orgdev;

	private String branchc;

	@Column(name = "ENC_KCV")
	private String enc_kcv;

	@Column(name = "ENC_KEY")
	private String enc_key;

	public long getOrgdev() {
		return orgdev;
	}

	public void setOrgdev(long orgdev) {
		this.orgdev = orgdev;
	}

	public String getBranchc() {
		return branchc;
	}

	public void setBranchc(String branchc) {
		this.branchc = branchc;
	}

	public String getEnc_kcv() {
		return enc_kcv;
	}

	public void setEnc_kcv(String enc_kcv) {
		this.enc_kcv = enc_kcv;
	}

	public String getEnc_key() {
		return enc_key;
	}

	public void setEnc_key(String enc_key) {
		this.enc_key = enc_key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXor_kcv() {
		return xor_kcv;
	}

	public void setXor_kcv(String xor_kcv) {
		this.xor_kcv = xor_kcv;
	}

	public String getXor_key() {
		return xor_key;
	}

	public void setXor_key(String xor_key) {
		this.xor_key = xor_key;
	}

	private String status;

	@Column(name = "XOR_KCV")
	private String xor_kcv;

	@Column(name = "XOR_KEY")
	private String xor_key;

	public TmkReqTab() {
	}

}