package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DEV_KEY_TAB database table.
 * 
 */
@Entity
@Table(name="DEV_KEY_TAB")
//@NamedQuery(name="DevKeyTab.findAll", query="SELECT d FROM DevKeyTab d")
public class DevKeyTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name="COMM_KEY")
	private String commKey;

	private String direction;

	@Column(name="KEY_LENGTH")
	private BigDecimal keyLength;

	@Column(name="MASTER_KEY")
	private String masterKey;

	@Column(name="PCI_COMPLIANT")
	private BigDecimal pciCompliant;

	@Column(name="PINBLK_FMT")
	private String pinblkFmt;

	private String pmkey;

	@Column(name="TMK_KCV")
	private String tmkKcv;

	@Column(name="TMK_LCHG")
	private String tmkLchg;

	@Column(name="TPK_KCV")
	private String tpkKcv;

	@Column(name="TPK_LCHG")
	private String tpkLchg;

	public DevKeyTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getCommKey() {
		return this.commKey;
	}

	public void setCommKey(String commKey) {
		this.commKey = commKey;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getKeyLength() {
		return this.keyLength;
	}

	public void setKeyLength(BigDecimal keyLength) {
		this.keyLength = keyLength;
	}

	public String getMasterKey() {
		return this.masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public BigDecimal getPciCompliant() {
		return this.pciCompliant;
	}

	public void setPciCompliant(BigDecimal pciCompliant) {
		this.pciCompliant = pciCompliant;
	}

	public String getPinblkFmt() {
		return this.pinblkFmt;
	}

	public void setPinblkFmt(String pinblkFmt) {
		this.pinblkFmt = pinblkFmt;
	}

	public String getPmkey() {
		return this.pmkey;
	}

	public void setPmkey(String pmkey) {
		this.pmkey = pmkey;
	}

	public String getTmkKcv() {
		return this.tmkKcv;
	}

	public void setTmkKcv(String tmkKcv) {
		this.tmkKcv = tmkKcv;
	}

	public String getTmkLchg() {
		return this.tmkLchg;
	}

	public void setTmkLchg(String tmkLchg) {
		this.tmkLchg = tmkLchg;
	}

	public String getTpkKcv() {
		return this.tpkKcv;
	}

	public void setTpkKcv(String tpkKcv) {
		this.tpkKcv = tpkKcv;
	}

	public String getTpkLchg() {
		return this.tpkLchg;
	}

	public void setTpkLchg(String tpkLchg) {
		this.tpkLchg = tpkLchg;
	}

}