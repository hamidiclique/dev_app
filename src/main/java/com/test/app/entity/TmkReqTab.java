package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TMK_REQ_TAB database table.
 * 
 */
@Entity
@Table(name="TMK_REQ_TAB")
//@NamedQuery(name="TmkReqTab.findAll", query="SELECT t FROM TmkReqTab t")
public class TmkReqTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long orgdev;

	private String branchc;

	@Column(name="ENC_KCV")
	private String encKcv;

	@Column(name="ENC_KEY")
	private String encKey;

	private String status;

	@Column(name="XOR_KCV")
	private String xorKcv;

	@Column(name="XOR_KEY")
	private String xorKey;

	public TmkReqTab() {
	}

	public long getOrgdev() {
		return this.orgdev;
	}

	public void setOrgdev(long orgdev) {
		this.orgdev = orgdev;
	}

	public String getBranchc() {
		return this.branchc;
	}

	public void setBranchc(String branchc) {
		this.branchc = branchc;
	}

	public String getEncKcv() {
		return this.encKcv;
	}

	public void setEncKcv(String encKcv) {
		this.encKcv = encKcv;
	}

	public String getEncKey() {
		return this.encKey;
	}

	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXorKcv() {
		return this.xorKcv;
	}

	public void setXorKcv(String xorKcv) {
		this.xorKcv = xorKcv;
	}

	public String getXorKey() {
		return this.xorKey;
	}

	public void setXorKey(String xorKey) {
		this.xorKey = xorKey;
	}

}