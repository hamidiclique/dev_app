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
	private long branch_id;

	@Column(name = "BRANCH_ADDR")
	private String branch_addr;

	@Column(name = "BRANCH_ADDR2")
	private String branch_addr2;

	@Column(name = "BRANCH_CITY")
	private String branch_city;

	@Column(name = "BRANCH_EMAIL1")
	private String branch_email1;

	@Column(name = "BRANCH_EMAIL2")
	private String branch_email2;

	@Column(name = "BRANCH_MOBILE")
	private String branch_mobile;

	@Column(name = "BRANCH_NAME")
	private String branch_name;

	@Column(name = "BRANCH_PHONE")
	private String branch_phone;

	private String iso;

	@Column(name = "KEY_CUSTODIAN1_NAME")
	private String key_custodian1_name;

	@Column(name = "KEY_CUSTODIAN1_TITLE")
	private String key_custodian1_title;

	@Column(name = "KEY_CUSTODIAN2_NAME")
	private String key_custodian2_name;

	@Column(name = "KEY_CUSTODIAN2_TITLE")
	private String key_custodian2_title;

	@Column(name = "OFFICER_NAME1")
	private String officer_name1;

	@Column(name = "OFFICER_NAME2")
	private String officer_name2;

	private BigDecimal region;

	@Column(name = "\"SEQUENCE\"")
	private BigDecimal sequence;

	@Column(name = "SEQUENCE_LEN")
	private BigDecimal sequence_len;

	public BranchTab() {
	}

	public long getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(long branch_id) {
		this.branch_id = branch_id;
	}

	public String getBranch_addr() {
		return branch_addr;
	}

	public void setBranch_addr(String branch_addr) {
		this.branch_addr = branch_addr;
	}

	public String getBranch_addr2() {
		return branch_addr2;
	}

	public void setBranch_addr2(String branch_addr2) {
		this.branch_addr2 = branch_addr2;
	}

	public String getBranch_city() {
		return branch_city;
	}

	public void setBranch_city(String branch_city) {
		this.branch_city = branch_city;
	}

	public String getBranch_email1() {
		return branch_email1;
	}

	public void setBranch_email1(String branch_email1) {
		this.branch_email1 = branch_email1;
	}

	public String getBranch_email2() {
		return branch_email2;
	}

	public void setBranch_email2(String branch_email2) {
		this.branch_email2 = branch_email2;
	}

	public String getBranch_mobile() {
		return branch_mobile;
	}

	public void setBranch_mobile(String branch_mobile) {
		this.branch_mobile = branch_mobile;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_phone() {
		return branch_phone;
	}

	public void setBranch_phone(String branch_phone) {
		this.branch_phone = branch_phone;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getKey_custodian1_name() {
		return key_custodian1_name;
	}

	public void setKey_custodian1_name(String key_custodian1_name) {
		this.key_custodian1_name = key_custodian1_name;
	}

	public String getKey_custodian1_title() {
		return key_custodian1_title;
	}

	public void setKey_custodian1_title(String key_custodian1_title) {
		this.key_custodian1_title = key_custodian1_title;
	}

	public String getKey_custodian2_name() {
		return key_custodian2_name;
	}

	public void setKey_custodian2_name(String key_custodian2_name) {
		this.key_custodian2_name = key_custodian2_name;
	}

	public String getKey_custodian2_title() {
		return key_custodian2_title;
	}

	public void setKey_custodian2_title(String key_custodian2_title) {
		this.key_custodian2_title = key_custodian2_title;
	}

	public String getOfficer_name1() {
		return officer_name1;
	}

	public void setOfficer_name1(String officer_name1) {
		this.officer_name1 = officer_name1;
	}

	public String getOfficer_name2() {
		return officer_name2;
	}

	public void setOfficer_name2(String officer_name2) {
		this.officer_name2 = officer_name2;
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

	public BigDecimal getSequence_len() {
		return sequence_len;
	}

	public void setSequence_len(BigDecimal sequence_len) {
		this.sequence_len = sequence_len;
	}

}