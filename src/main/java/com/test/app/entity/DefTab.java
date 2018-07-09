package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the DEF_TAB database table.
 * 
 */
@Entity
@Table(name = "DEF_TAB")
// @NamedQuery(name="DefTab.findAll", query="SELECT d FROM DefTab d")
public class DefTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String acct;

	private BigDecimal branch;

	private BigDecimal bwarn;

	private String circuit;

	private String city;

	private BigDecimal d1bill;

	private BigDecimal d1type;

	private BigDecimal d1val;

	private BigDecimal d2bill;

	private BigDecimal d2type;

	private BigDecimal d2val;

	private BigDecimal d3bill;

	private BigDecimal d3type;

	private BigDecimal d3val;

	private BigDecimal d4bill;

	private BigDecimal d4type;

	private BigDecimal d4val;

	private BigDecimal dwarn;

	private String ecp;

	@Column(name = "GMT_OFFSET")
	private BigDecimal gmt_offset;

	private String loader;

	private BigDecimal luno;

	private BigDecimal macing;

	@Column(name = "MERCH_ID")
	private String merch_id;

	private BigDecimal numdisp;

	private BigDecimal rec;

	private BigDecimal rjwarn;

	private BigDecimal rwarn;

	@Column(name = "SECURITY_MOD")
	private BigDecimal security_mod;

	@Column(name = "SERV_UNIT_CODE")
	private BigDecimal serv_unit_code;

	@Column(name = "\"STATE\"")
	private String state;

	private String street;

	private String tchar;

	private BigDecimal termchar;

	private String tid;

	private String time1;

	private String time2;

	private String time3;

	private String time4;

	private String zip;

	public DefTab() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAcct() {
		return this.acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	public BigDecimal getBranch() {
		return this.branch;
	}

	public void setBranch(BigDecimal branch) {
		this.branch = branch;
	}

	public BigDecimal getBwarn() {
		return this.bwarn;
	}

	public void setBwarn(BigDecimal bwarn) {
		this.bwarn = bwarn;
	}

	public String getCircuit() {
		return this.circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public BigDecimal getD1bill() {
		return this.d1bill;
	}

	public void setD1bill(BigDecimal d1bill) {
		this.d1bill = d1bill;
	}

	public BigDecimal getD1type() {
		return this.d1type;
	}

	public void setD1type(BigDecimal d1type) {
		this.d1type = d1type;
	}

	public BigDecimal getD1val() {
		return this.d1val;
	}

	public void setD1val(BigDecimal d1val) {
		this.d1val = d1val;
	}

	public BigDecimal getD2bill() {
		return this.d2bill;
	}

	public void setD2bill(BigDecimal d2bill) {
		this.d2bill = d2bill;
	}

	public BigDecimal getD2type() {
		return this.d2type;
	}

	public void setD2type(BigDecimal d2type) {
		this.d2type = d2type;
	}

	public BigDecimal getD2val() {
		return this.d2val;
	}

	public void setD2val(BigDecimal d2val) {
		this.d2val = d2val;
	}

	public BigDecimal getD3bill() {
		return this.d3bill;
	}

	public void setD3bill(BigDecimal d3bill) {
		this.d3bill = d3bill;
	}

	public BigDecimal getD3type() {
		return this.d3type;
	}

	public void setD3type(BigDecimal d3type) {
		this.d3type = d3type;
	}

	public BigDecimal getD3val() {
		return this.d3val;
	}

	public void setD3val(BigDecimal d3val) {
		this.d3val = d3val;
	}

	public BigDecimal getD4bill() {
		return this.d4bill;
	}

	public void setD4bill(BigDecimal d4bill) {
		this.d4bill = d4bill;
	}

	public BigDecimal getD4type() {
		return this.d4type;
	}

	public void setD4type(BigDecimal d4type) {
		this.d4type = d4type;
	}

	public BigDecimal getD4val() {
		return this.d4val;
	}

	public void setD4val(BigDecimal d4val) {
		this.d4val = d4val;
	}

	public BigDecimal getDwarn() {
		return this.dwarn;
	}

	public void setDwarn(BigDecimal dwarn) {
		this.dwarn = dwarn;
	}

	public String getEcp() {
		return this.ecp;
	}

	public void setEcp(String ecp) {
		this.ecp = ecp;
	}

	public String getLoader() {
		return this.loader;
	}

	public void setLoader(String loader) {
		this.loader = loader;
	}

	public BigDecimal getLuno() {
		return this.luno;
	}

	public void setLuno(BigDecimal luno) {
		this.luno = luno;
	}

	public BigDecimal getMacing() {
		return this.macing;
	}

	public void setMacing(BigDecimal macing) {
		this.macing = macing;
	}

	public BigDecimal getNumdisp() {
		return this.numdisp;
	}

	public void setNumdisp(BigDecimal numdisp) {
		this.numdisp = numdisp;
	}

	public BigDecimal getRec() {
		return this.rec;
	}

	public void setRec(BigDecimal rec) {
		this.rec = rec;
	}

	public BigDecimal getRjwarn() {
		return this.rjwarn;
	}

	public void setRjwarn(BigDecimal rjwarn) {
		this.rjwarn = rjwarn;
	}

	public BigDecimal getRwarn() {
		return this.rwarn;
	}

	public void setRwarn(BigDecimal rwarn) {
		this.rwarn = rwarn;
	}

	public BigDecimal getGmt_offset() {
		return gmt_offset;
	}

	public void setGmt_offset(BigDecimal gmt_offset) {
		this.gmt_offset = gmt_offset;
	}

	public String getMerch_id() {
		return merch_id;
	}

	public void setMerch_id(String merch_id) {
		this.merch_id = merch_id;
	}

	public BigDecimal getSecurity_mod() {
		return security_mod;
	}

	public void setSecurity_mod(BigDecimal security_mod) {
		this.security_mod = security_mod;
	}

	public BigDecimal getServ_unit_code() {
		return serv_unit_code;
	}

	public void setServ_unit_code(BigDecimal serv_unit_code) {
		this.serv_unit_code = serv_unit_code;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTchar() {
		return this.tchar;
	}

	public void setTchar(String tchar) {
		this.tchar = tchar;
	}

	public BigDecimal getTermchar() {
		return this.termchar;
	}

	public void setTermchar(BigDecimal termchar) {
		this.termchar = termchar;
	}

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTime1() {
		return this.time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return this.time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getTime3() {
		return this.time3;
	}

	public void setTime3(String time3) {
		this.time3 = time3;
	}

	public String getTime4() {
		return this.time4;
	}

	public void setTime4(String time4) {
		this.time4 = time4;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}