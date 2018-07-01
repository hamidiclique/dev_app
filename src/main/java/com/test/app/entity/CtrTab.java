package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CTR_TAB database table.
 * 
 */
@Entity
@Table(name="CTR_TAB")
//@NamedQuery(name="CtrTab.findAll", query="SELECT c FROM CtrTab c")
public class CtrTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name="C_C1BILLS")
	private BigDecimal cC1bills;

	@Column(name="C_C1BREJ")
	private BigDecimal cC1brej;

	@Column(name="C_C2BILLS")
	private BigDecimal cC2bills;

	@Column(name="C_C2BREJ")
	private BigDecimal cC2brej;

	@Column(name="C_C3BILLS")
	private BigDecimal cC3bills;

	@Column(name="C_C3BREJ")
	private BigDecimal cC3brej;

	@Column(name="C_C4BILLS")
	private BigDecimal cC4bills;

	@Column(name="C_C4BREJ")
	private BigDecimal cC4brej;

	@Column(name="C_CACSHA")
	private BigDecimal cCacsha;

	@Column(name="C_CAINQ")
	private BigDecimal cCainq;

	@Column(name="C_CAXFER")
	private BigDecimal cCaxfer;

	@Column(name="C_CCAPT")
	private BigDecimal cCcapt;

	@Column(name="C_CCKBK")
	private BigDecimal cCckbk;

	@Column(name="C_CDEP")
	private BigDecimal cCdep;

	@Column(name="C_CDEPAC")
	private BigDecimal cCdepac;

	@Column(name="C_CFRF")
	private String cCfrf;

	@Column(name="C_CFSEG")
	private BigDecimal cCfseg;

	@Column(name="C_CICSHA")
	private BigDecimal cCicsha;

	@Column(name="C_CIINQ")
	private BigDecimal cCiinq;

	@Column(name="C_CINQ")
	private BigDecimal cCinq;

	@Column(name="C_CIXFER")
	private BigDecimal cCixfer;

	@Column(name="C_CPMT")
	private BigDecimal cCpmt;

	@Column(name="C_CRECT")
	private BigDecimal cCrect;

	@Column(name="C_CSVCLOAD")
	private BigDecimal cCsvcload;

	@Column(name="C_CSVCUNLOAD")
	private BigDecimal cCsvcunload;

	@Column(name="C_CTRANS")
	private BigDecimal cCtrans;

	@Column(name="C_CWDL_LOC")
	private BigDecimal cCwdlLoc;

	@Column(name="C_CWDL_US")
	private BigDecimal cCwdlUs;

	@Column(name="C_CXFER")
	private BigDecimal cCxfer;

	@Column(name="C_TACSHA_LOC")
	private BigDecimal cTacshaLoc;

	@Column(name="C_TACSHA_US")
	private BigDecimal cTacshaUs;

	@Column(name="C_TDEP")
	private BigDecimal cTdep;

	@Column(name="C_TDEPC")
	private BigDecimal cTdepc;

	@Column(name="C_TDEPS")
	private BigDecimal cTdeps;

	@Column(name="C_TICSHA")
	private BigDecimal cTicsha;

	@Column(name="C_TPMT")
	private BigDecimal cTpmt;

	@Column(name="C_TPMTL")
	private BigDecimal cTpmtl;

	@Column(name="C_TPMTMC")
	private BigDecimal cTpmtmc;

	@Column(name="C_TSVCLOAD")
	private BigDecimal cTsvcload;

	@Column(name="C_TSVCUNLOAD")
	private BigDecimal cTsvcunload;

	@Column(name="C_TWDL_LOC")
	private BigDecimal cTwdlLoc;

	@Column(name="C_TWDL_US")
	private BigDecimal cTwdlUs;

	@Column(name="C_TWDLC_LOC")
	private BigDecimal cTwdlcLoc;

	@Column(name="C_TWDLC_US")
	private BigDecimal cTwdlcUs;

	@Column(name="C_TWDLS_LOC")
	private BigDecimal cTwdlsLoc;

	@Column(name="C_TWDLS_US")
	private BigDecimal cTwdlsUs;

	@Column(name="C1_START_BILLS")
	private BigDecimal c1StartBills;

	@Column(name="C2_START_BILLS")
	private BigDecimal c2StartBills;

	@Column(name="C3_START_BILLS")
	private BigDecimal c3StartBills;

	@Column(name="C4_START_BILLS")
	private BigDecimal c4StartBills;

	public CtrTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getCC1bills() {
		return this.cC1bills;
	}

	public void setCC1bills(BigDecimal cC1bills) {
		this.cC1bills = cC1bills;
	}

	public BigDecimal getCC1brej() {
		return this.cC1brej;
	}

	public void setCC1brej(BigDecimal cC1brej) {
		this.cC1brej = cC1brej;
	}

	public BigDecimal getCC2bills() {
		return this.cC2bills;
	}

	public void setCC2bills(BigDecimal cC2bills) {
		this.cC2bills = cC2bills;
	}

	public BigDecimal getCC2brej() {
		return this.cC2brej;
	}

	public void setCC2brej(BigDecimal cC2brej) {
		this.cC2brej = cC2brej;
	}

	public BigDecimal getCC3bills() {
		return this.cC3bills;
	}

	public void setCC3bills(BigDecimal cC3bills) {
		this.cC3bills = cC3bills;
	}

	public BigDecimal getCC3brej() {
		return this.cC3brej;
	}

	public void setCC3brej(BigDecimal cC3brej) {
		this.cC3brej = cC3brej;
	}

	public BigDecimal getCC4bills() {
		return this.cC4bills;
	}

	public void setCC4bills(BigDecimal cC4bills) {
		this.cC4bills = cC4bills;
	}

	public BigDecimal getCC4brej() {
		return this.cC4brej;
	}

	public void setCC4brej(BigDecimal cC4brej) {
		this.cC4brej = cC4brej;
	}

	public BigDecimal getCCacsha() {
		return this.cCacsha;
	}

	public void setCCacsha(BigDecimal cCacsha) {
		this.cCacsha = cCacsha;
	}

	public BigDecimal getCCainq() {
		return this.cCainq;
	}

	public void setCCainq(BigDecimal cCainq) {
		this.cCainq = cCainq;
	}

	public BigDecimal getCCaxfer() {
		return this.cCaxfer;
	}

	public void setCCaxfer(BigDecimal cCaxfer) {
		this.cCaxfer = cCaxfer;
	}

	public BigDecimal getCCcapt() {
		return this.cCcapt;
	}

	public void setCCcapt(BigDecimal cCcapt) {
		this.cCcapt = cCcapt;
	}

	public BigDecimal getCCckbk() {
		return this.cCckbk;
	}

	public void setCCckbk(BigDecimal cCckbk) {
		this.cCckbk = cCckbk;
	}

	public BigDecimal getCCdep() {
		return this.cCdep;
	}

	public void setCCdep(BigDecimal cCdep) {
		this.cCdep = cCdep;
	}

	public BigDecimal getCCdepac() {
		return this.cCdepac;
	}

	public void setCCdepac(BigDecimal cCdepac) {
		this.cCdepac = cCdepac;
	}

	public String getCCfrf() {
		return this.cCfrf;
	}

	public void setCCfrf(String cCfrf) {
		this.cCfrf = cCfrf;
	}

	public BigDecimal getCCfseg() {
		return this.cCfseg;
	}

	public void setCCfseg(BigDecimal cCfseg) {
		this.cCfseg = cCfseg;
	}

	public BigDecimal getCCicsha() {
		return this.cCicsha;
	}

	public void setCCicsha(BigDecimal cCicsha) {
		this.cCicsha = cCicsha;
	}

	public BigDecimal getCCiinq() {
		return this.cCiinq;
	}

	public void setCCiinq(BigDecimal cCiinq) {
		this.cCiinq = cCiinq;
	}

	public BigDecimal getCCinq() {
		return this.cCinq;
	}

	public void setCCinq(BigDecimal cCinq) {
		this.cCinq = cCinq;
	}

	public BigDecimal getCCixfer() {
		return this.cCixfer;
	}

	public void setCCixfer(BigDecimal cCixfer) {
		this.cCixfer = cCixfer;
	}

	public BigDecimal getCCpmt() {
		return this.cCpmt;
	}

	public void setCCpmt(BigDecimal cCpmt) {
		this.cCpmt = cCpmt;
	}

	public BigDecimal getCCrect() {
		return this.cCrect;
	}

	public void setCCrect(BigDecimal cCrect) {
		this.cCrect = cCrect;
	}

	public BigDecimal getCCsvcload() {
		return this.cCsvcload;
	}

	public void setCCsvcload(BigDecimal cCsvcload) {
		this.cCsvcload = cCsvcload;
	}

	public BigDecimal getCCsvcunload() {
		return this.cCsvcunload;
	}

	public void setCCsvcunload(BigDecimal cCsvcunload) {
		this.cCsvcunload = cCsvcunload;
	}

	public BigDecimal getCCtrans() {
		return this.cCtrans;
	}

	public void setCCtrans(BigDecimal cCtrans) {
		this.cCtrans = cCtrans;
	}

	public BigDecimal getCCwdlLoc() {
		return this.cCwdlLoc;
	}

	public void setCCwdlLoc(BigDecimal cCwdlLoc) {
		this.cCwdlLoc = cCwdlLoc;
	}

	public BigDecimal getCCwdlUs() {
		return this.cCwdlUs;
	}

	public void setCCwdlUs(BigDecimal cCwdlUs) {
		this.cCwdlUs = cCwdlUs;
	}

	public BigDecimal getCCxfer() {
		return this.cCxfer;
	}

	public void setCCxfer(BigDecimal cCxfer) {
		this.cCxfer = cCxfer;
	}

	public BigDecimal getCTacshaLoc() {
		return this.cTacshaLoc;
	}

	public void setCTacshaLoc(BigDecimal cTacshaLoc) {
		this.cTacshaLoc = cTacshaLoc;
	}

	public BigDecimal getCTacshaUs() {
		return this.cTacshaUs;
	}

	public void setCTacshaUs(BigDecimal cTacshaUs) {
		this.cTacshaUs = cTacshaUs;
	}

	public BigDecimal getCTdep() {
		return this.cTdep;
	}

	public void setCTdep(BigDecimal cTdep) {
		this.cTdep = cTdep;
	}

	public BigDecimal getCTdepc() {
		return this.cTdepc;
	}

	public void setCTdepc(BigDecimal cTdepc) {
		this.cTdepc = cTdepc;
	}

	public BigDecimal getCTdeps() {
		return this.cTdeps;
	}

	public void setCTdeps(BigDecimal cTdeps) {
		this.cTdeps = cTdeps;
	}

	public BigDecimal getCTicsha() {
		return this.cTicsha;
	}

	public void setCTicsha(BigDecimal cTicsha) {
		this.cTicsha = cTicsha;
	}

	public BigDecimal getCTpmt() {
		return this.cTpmt;
	}

	public void setCTpmt(BigDecimal cTpmt) {
		this.cTpmt = cTpmt;
	}

	public BigDecimal getCTpmtl() {
		return this.cTpmtl;
	}

	public void setCTpmtl(BigDecimal cTpmtl) {
		this.cTpmtl = cTpmtl;
	}

	public BigDecimal getCTpmtmc() {
		return this.cTpmtmc;
	}

	public void setCTpmtmc(BigDecimal cTpmtmc) {
		this.cTpmtmc = cTpmtmc;
	}

	public BigDecimal getCTsvcload() {
		return this.cTsvcload;
	}

	public void setCTsvcload(BigDecimal cTsvcload) {
		this.cTsvcload = cTsvcload;
	}

	public BigDecimal getCTsvcunload() {
		return this.cTsvcunload;
	}

	public void setCTsvcunload(BigDecimal cTsvcunload) {
		this.cTsvcunload = cTsvcunload;
	}

	public BigDecimal getCTwdlLoc() {
		return this.cTwdlLoc;
	}

	public void setCTwdlLoc(BigDecimal cTwdlLoc) {
		this.cTwdlLoc = cTwdlLoc;
	}

	public BigDecimal getCTwdlUs() {
		return this.cTwdlUs;
	}

	public void setCTwdlUs(BigDecimal cTwdlUs) {
		this.cTwdlUs = cTwdlUs;
	}

	public BigDecimal getCTwdlcLoc() {
		return this.cTwdlcLoc;
	}

	public void setCTwdlcLoc(BigDecimal cTwdlcLoc) {
		this.cTwdlcLoc = cTwdlcLoc;
	}

	public BigDecimal getCTwdlcUs() {
		return this.cTwdlcUs;
	}

	public void setCTwdlcUs(BigDecimal cTwdlcUs) {
		this.cTwdlcUs = cTwdlcUs;
	}

	public BigDecimal getCTwdlsLoc() {
		return this.cTwdlsLoc;
	}

	public void setCTwdlsLoc(BigDecimal cTwdlsLoc) {
		this.cTwdlsLoc = cTwdlsLoc;
	}

	public BigDecimal getCTwdlsUs() {
		return this.cTwdlsUs;
	}

	public void setCTwdlsUs(BigDecimal cTwdlsUs) {
		this.cTwdlsUs = cTwdlsUs;
	}

	public BigDecimal getC1StartBills() {
		return this.c1StartBills;
	}

	public void setC1StartBills(BigDecimal c1StartBills) {
		this.c1StartBills = c1StartBills;
	}

	public BigDecimal getC2StartBills() {
		return this.c2StartBills;
	}

	public void setC2StartBills(BigDecimal c2StartBills) {
		this.c2StartBills = c2StartBills;
	}

	public BigDecimal getC3StartBills() {
		return this.c3StartBills;
	}

	public void setC3StartBills(BigDecimal c3StartBills) {
		this.c3StartBills = c3StartBills;
	}

	public BigDecimal getC4StartBills() {
		return this.c4StartBills;
	}

	public void setC4StartBills(BigDecimal c4StartBills) {
		this.c4StartBills = c4StartBills;
	}

}