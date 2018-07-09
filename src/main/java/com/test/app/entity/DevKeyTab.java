package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the DEV_KEY_TAB database table.
 * 
 */
@Entity
@Table(name = "DEV_KEY_TAB")
// @NamedQuery(name="DevKeyTab.findAll", query="SELECT d FROM DevKeyTab d")
public class DevKeyTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name = "COMM_KEY")
	private String comm_key;

	private String direction;

	@Column(name = "KEY_LENGTH")
	private BigDecimal key_length;

	@Column(name = "MASTER_KEY")
	private String master_key;

	@Column(name = "PCI_COMPLIANT")
	private BigDecimal pci_compliant;

	@Column(name = "PINBLK_FMT")
	private String pinblk_fmt;

	private String pmkey;

	@Column(name = "TMK_KCV")
	private String tmk_kcv;

	@Column(name = "TMK_LCHG")
	private String tmk_lchg;

	@Column(name = "TPK_KCV")
	private String tpk_kcv;

	@Column(name = "TPK_LCHG")
	private String tpk_lchg;

	public DevKeyTab() {
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getComm_key() {
		return comm_key;
	}

	public void setComm_key(String comm_key) {
		this.comm_key = comm_key;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getKey_length() {
		return key_length;
	}

	public void setKey_length(BigDecimal key_length) {
		this.key_length = key_length;
	}

	public String getMaster_key() {
		return master_key;
	}

	public void setMaster_key(String master_key) {
		this.master_key = master_key;
	}

	public BigDecimal getPci_compliant() {
		return pci_compliant;
	}

	public void setPci_compliant(BigDecimal pci_compliant) {
		this.pci_compliant = pci_compliant;
	}

	public String getPinblk_fmt() {
		return pinblk_fmt;
	}

	public void setPinblk_fmt(String pinblk_fmt) {
		this.pinblk_fmt = pinblk_fmt;
	}

	public String getPmkey() {
		return pmkey;
	}

	public void setPmkey(String pmkey) {
		this.pmkey = pmkey;
	}

	public String getTmk_kcv() {
		return tmk_kcv;
	}

	public void setTmk_kcv(String tmk_kcv) {
		this.tmk_kcv = tmk_kcv;
	}

	public String getTmk_lchg() {
		return tmk_lchg;
	}

	public void setTmk_lchg(String tmk_lchg) {
		this.tmk_lchg = tmk_lchg;
	}

	public String getTpk_kcv() {
		return tpk_kcv;
	}

	public void setTpk_kcv(String tpk_kcv) {
		this.tpk_kcv = tpk_kcv;
	}

	public String getTpk_lchg() {
		return tpk_lchg;
	}

	public void setTpk_lchg(String tpk_lchg) {
		this.tpk_lchg = tpk_lchg;
	}

}