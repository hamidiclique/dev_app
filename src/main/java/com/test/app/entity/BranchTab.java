package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the BRANCH_TAB database table.
 * 
 */
@Entity
@Table(name = "BRANCH_TAB")
// @NamedQuery(name = "BranchTab.findAll", query = "SELECT b FROM BranchTab b")
public class BranchTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BRANCH_ID")
	private long branchId;

	@Column(name = "BRANCH_ADDR")
	private String branchAddr;

	@Column(name = "BRANCH_ADDR2")
	private String branchAddr2;

	@Column(name = "BRANCH_CITY")
	private String branchCity;

	@Column(name = "BRANCH_EMAIL1")
	private String branchEmail1;

	@Column(name = "BRANCH_EMAIL2")
	private String branchEmail2;

	@Column(name = "BRANCH_MOBILE")
	private String branchMobile;

	@Column(name = "BRANCH_NAME")
	private String branchName;

	@Column(name = "BRANCH_PHONE")
	private String branchPhone;

	private String iso;

	@Column(name = "KEY_CUSTODIAN1_NAME")
	private String keyCustodian1Name;

	@Column(name = "KEY_CUSTODIAN1_TITLE")
	private String keyCustodian1Title;

	@Column(name = "KEY_CUSTODIAN2_NAME")
	private String keyCustodian2Name;

	@Column(name = "KEY_CUSTODIAN2_TITLE")
	private String keyCustodian2Title;

	@Column(name = "OFFICER_NAME1")
	private String officerName1;

	@Column(name = "OFFICER_NAME2")
	private String officerName2;

	private BigDecimal region;

	@Column(name = "\"SEQUENCE\"")
	private BigDecimal sequence;

	@Column(name = "SEQUENCE_LEN")
	private BigDecimal sequenceLen;

	public BranchTab() {
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getBranchAddr() {
		return branchAddr;
	}

	public void setBranchAddr(String branchAddr) {
		this.branchAddr = branchAddr;
	}

	public String getBranchAddr2() {
		return branchAddr2;
	}

	public void setBranchAddr2(String branchAddr2) {
		this.branchAddr2 = branchAddr2;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchEmail1() {
		return branchEmail1;
	}

	public void setBranchEmail1(String branchEmail1) {
		this.branchEmail1 = branchEmail1;
	}

	public String getBranchEmail2() {
		return branchEmail2;
	}

	public void setBranchEmail2(String branchEmail2) {
		this.branchEmail2 = branchEmail2;
	}

	public String getBranchMobile() {
		return branchMobile;
	}

	public void setBranchMobile(String branchMobile) {
		this.branchMobile = branchMobile;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchPhone() {
		return branchPhone;
	}

	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getKeyCustodian1Name() {
		return keyCustodian1Name;
	}

	public void setKeyCustodian1Name(String keyCustodian1Name) {
		this.keyCustodian1Name = keyCustodian1Name;
	}

	public String getKeyCustodian1Title() {
		return keyCustodian1Title;
	}

	public void setKeyCustodian1Title(String keyCustodian1Title) {
		this.keyCustodian1Title = keyCustodian1Title;
	}

	public String getKeyCustodian2Name() {
		return keyCustodian2Name;
	}

	public void setKeyCustodian2Name(String keyCustodian2Name) {
		this.keyCustodian2Name = keyCustodian2Name;
	}

	public String getKeyCustodian2Title() {
		return keyCustodian2Title;
	}

	public void setKeyCustodian2Title(String keyCustodian2Title) {
		this.keyCustodian2Title = keyCustodian2Title;
	}

	public String getOfficerName1() {
		return officerName1;
	}

	public void setOfficerName1(String officerName1) {
		this.officerName1 = officerName1;
	}

	public String getOfficerName2() {
		return officerName2;
	}

	public void setOfficerName2(String officerName2) {
		this.officerName2 = officerName2;
	}

	public BigDecimal getRegion() {
		return region;
	}

	public void setRegion(BigDecimal region) {
		this.region = region;
	}

	public BigDecimal getSequence() {
		return sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}

	public BigDecimal getSequenceLen() {
		return sequenceLen;
	}

	public void setSequenceLen(BigDecimal sequenceLen) {
		this.sequenceLen = sequenceLen;
	}

}