package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the CTR_TAB database table.
 * 
 */
@Entity
@Table(name = "CTR_TAB")
// @NamedQuery(name="CtrTab.findAll", query="SELECT c FROM CtrTab c")
public class CtrTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	@Column(name = "C_C1BILLS")
	private BigDecimal c_c1bills;

	@Column(name = "C_C1BREJ")
	private BigDecimal c_c1brej;

	@Column(name = "C_C2BILLS")
	private BigDecimal c_c2bills;

	@Column(name = "C_C2BREJ")
	private BigDecimal c_c2brej;

	@Column(name = "C_C3BILLS")
	private BigDecimal c_c3bills;

	@Column(name = "C_C3BREJ")
	private BigDecimal c_c3brej;

	@Column(name = "C_C4BILLS")
	private BigDecimal c_c4bills;

	@Column(name = "C_C4BREJ")
	private BigDecimal c_c4brej;

	@Column(name = "C_CACSHA")
	private BigDecimal c_cacsha;

	@Column(name = "C_CAINQ")
	private BigDecimal c_cainq;

	@Column(name = "C_CAXFER")
	private BigDecimal c_caxfer;

	@Column(name = "C_CCAPT")
	private BigDecimal c_ccapt;

	@Column(name = "C_CCKBK")
	private BigDecimal c_cckbk;

	@Column(name = "C_CDEP")
	private BigDecimal c_cdep;

	@Column(name = "C_CDEPAC")
	private BigDecimal c_cdepac;

	@Column(name = "C_CFRF")
	private String c_cfrf;

	@Column(name = "C_CFSEG")
	private BigDecimal c_cfseg;

	@Column(name = "C_CICSHA")
	private BigDecimal c_cicsha;

	@Column(name = "C_CIINQ")
	private BigDecimal c_ciinq;

	@Column(name = "C_CINQ")
	private BigDecimal c_cinq;

	@Column(name = "C_CIXFER")
	private BigDecimal c_cixfer;

	@Column(name = "C_CPMT")
	private BigDecimal c_cpmt;

	@Column(name = "C_CRECT")
	private BigDecimal c_crect;

	@Column(name = "C_CSVCLOAD")
	private BigDecimal c_csvcload;

	@Column(name = "C_CSVCUNLOAD")
	private BigDecimal c_csvcunload;

	@Column(name = "C_CTRANS")
	private BigDecimal c_ctrans;

	@Column(name = "C_CWDL_LOC")
	private BigDecimal c_cwdl_loc;

	@Column(name = "C_CWDL_US")
	private BigDecimal c_cwdl_us;

	@Column(name = "C_CXFER")
	private BigDecimal c_cxfer;

	@Column(name = "C_TACSHA_LOC")
	private BigDecimal c_tacsha_loc;

	@Column(name = "C_TACSHA_US")
	private BigDecimal c_tacsha_us;

	@Column(name = "C_TDEP")
	private BigDecimal c_tdep;

	@Column(name = "C_TDEPC")
	private BigDecimal c_tdepc;

	@Column(name = "C_TDEPS")
	private BigDecimal c_tdeps;

	@Column(name = "C_TICSHA")
	private BigDecimal c_ticsha;

	@Column(name = "C_TPMT")
	private BigDecimal c_tpmt;

	@Column(name = "C_TPMTL")
	private BigDecimal c_tpmtl;

	@Column(name = "C_TPMTMC")
	private BigDecimal c_tpmtmc;

	@Column(name = "C_TSVCLOAD")
	private BigDecimal c_tsvcload;

	@Column(name = "C_TSVCUNLOAD")
	private BigDecimal c_tsvcunload;

	@Column(name = "C_TWDL_LOC")
	private BigDecimal c_twdl_loc;

	@Column(name = "C_TWDL_US")
	private BigDecimal c_twdl_us;

	@Column(name = "C_TWDLC_LOC")
	private BigDecimal c_twdlc_loc;

	@Column(name = "C_TWDLC_US")
	private BigDecimal c_twdlc_us;

	@Column(name = "C_TWDLS_LOC")
	private BigDecimal c_twdls_loc;

	@Column(name = "C_TWDLS_US")
	private BigDecimal c_twdls_us;

	@Column(name = "C1_START_BILLS")
	private BigDecimal c1_start_bills;

	@Column(name = "C2_START_BILLS")
	private BigDecimal c2_start_bills;

	@Column(name = "C3_START_BILLS")
	private BigDecimal c3_start_bills;

	@Column(name = "C4_START_BILLS")
	private BigDecimal c4_start_bills;

	public CtrTab() {
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getC_c1bills() {
		return c_c1bills;
	}

	public void setC_c1bills(BigDecimal c_c1bills) {
		this.c_c1bills = c_c1bills;
	}

	public BigDecimal getC_c1brej() {
		return c_c1brej;
	}

	public void setC_c1brej(BigDecimal c_c1brej) {
		this.c_c1brej = c_c1brej;
	}

	public BigDecimal getC_c2bills() {
		return c_c2bills;
	}

	public void setC_c2bills(BigDecimal c_c2bills) {
		this.c_c2bills = c_c2bills;
	}

	public BigDecimal getC_c2brej() {
		return c_c2brej;
	}

	public void setC_c2brej(BigDecimal c_c2brej) {
		this.c_c2brej = c_c2brej;
	}

	public BigDecimal getC_c3bills() {
		return c_c3bills;
	}

	public void setC_c3bills(BigDecimal c_c3bills) {
		this.c_c3bills = c_c3bills;
	}

	public BigDecimal getC_c3brej() {
		return c_c3brej;
	}

	public void setC_c3brej(BigDecimal c_c3brej) {
		this.c_c3brej = c_c3brej;
	}

	public BigDecimal getC_c4bills() {
		return c_c4bills;
	}

	public void setC_c4bills(BigDecimal c_c4bills) {
		this.c_c4bills = c_c4bills;
	}

	public BigDecimal getC_c4brej() {
		return c_c4brej;
	}

	public void setC_c4brej(BigDecimal c_c4brej) {
		this.c_c4brej = c_c4brej;
	}

	public BigDecimal getC_cacsha() {
		return c_cacsha;
	}

	public void setC_cacsha(BigDecimal c_cacsha) {
		this.c_cacsha = c_cacsha;
	}

	public BigDecimal getC_cainq() {
		return c_cainq;
	}

	public void setC_cainq(BigDecimal c_cainq) {
		this.c_cainq = c_cainq;
	}

	public BigDecimal getC_caxfer() {
		return c_caxfer;
	}

	public void setC_caxfer(BigDecimal c_caxfer) {
		this.c_caxfer = c_caxfer;
	}

	public BigDecimal getC_ccapt() {
		return c_ccapt;
	}

	public void setC_ccapt(BigDecimal c_ccapt) {
		this.c_ccapt = c_ccapt;
	}

	public BigDecimal getC_cckbk() {
		return c_cckbk;
	}

	public void setC_cckbk(BigDecimal c_cckbk) {
		this.c_cckbk = c_cckbk;
	}

	public BigDecimal getC_cdep() {
		return c_cdep;
	}

	public void setC_cdep(BigDecimal c_cdep) {
		this.c_cdep = c_cdep;
	}

	public BigDecimal getC_cdepac() {
		return c_cdepac;
	}

	public void setC_cdepac(BigDecimal c_cdepac) {
		this.c_cdepac = c_cdepac;
	}

	public String getC_cfrf() {
		return c_cfrf;
	}

	public void setC_cfrf(String c_cfrf) {
		this.c_cfrf = c_cfrf;
	}

	public BigDecimal getC_cfseg() {
		return c_cfseg;
	}

	public void setC_cfseg(BigDecimal c_cfseg) {
		this.c_cfseg = c_cfseg;
	}

	public BigDecimal getC_cicsha() {
		return c_cicsha;
	}

	public void setC_cicsha(BigDecimal c_cicsha) {
		this.c_cicsha = c_cicsha;
	}

	public BigDecimal getC_ciinq() {
		return c_ciinq;
	}

	public void setC_ciinq(BigDecimal c_ciinq) {
		this.c_ciinq = c_ciinq;
	}

	public BigDecimal getC_cinq() {
		return c_cinq;
	}

	public void setC_cinq(BigDecimal c_cinq) {
		this.c_cinq = c_cinq;
	}

	public BigDecimal getC_cixfer() {
		return c_cixfer;
	}

	public void setC_cixfer(BigDecimal c_cixfer) {
		this.c_cixfer = c_cixfer;
	}

	public BigDecimal getC_cpmt() {
		return c_cpmt;
	}

	public void setC_cpmt(BigDecimal c_cpmt) {
		this.c_cpmt = c_cpmt;
	}

	public BigDecimal getC_crect() {
		return c_crect;
	}

	public void setC_crect(BigDecimal c_crect) {
		this.c_crect = c_crect;
	}

	public BigDecimal getC_csvcload() {
		return c_csvcload;
	}

	public void setC_csvcload(BigDecimal c_csvcload) {
		this.c_csvcload = c_csvcload;
	}

	public BigDecimal getC_csvcunload() {
		return c_csvcunload;
	}

	public void setC_csvcunload(BigDecimal c_csvcunload) {
		this.c_csvcunload = c_csvcunload;
	}

	public BigDecimal getC_ctrans() {
		return c_ctrans;
	}

	public void setC_ctrans(BigDecimal c_ctrans) {
		this.c_ctrans = c_ctrans;
	}

	public BigDecimal getC_cwdl_loc() {
		return c_cwdl_loc;
	}

	public void setC_cwdl_loc(BigDecimal c_cwdl_loc) {
		this.c_cwdl_loc = c_cwdl_loc;
	}

	public BigDecimal getC_cwdl_us() {
		return c_cwdl_us;
	}

	public void setC_cwdl_us(BigDecimal c_cwdl_us) {
		this.c_cwdl_us = c_cwdl_us;
	}

	public BigDecimal getC_cxfer() {
		return c_cxfer;
	}

	public void setC_cxfer(BigDecimal c_cxfer) {
		this.c_cxfer = c_cxfer;
	}

	public BigDecimal getC_tacsha_loc() {
		return c_tacsha_loc;
	}

	public void setC_tacsha_loc(BigDecimal c_tacsha_loc) {
		this.c_tacsha_loc = c_tacsha_loc;
	}

	public BigDecimal getC_tacsha_us() {
		return c_tacsha_us;
	}

	public void setC_tacsha_us(BigDecimal c_tacsha_us) {
		this.c_tacsha_us = c_tacsha_us;
	}

	public BigDecimal getC_tdep() {
		return c_tdep;
	}

	public void setC_tdep(BigDecimal c_tdep) {
		this.c_tdep = c_tdep;
	}

	public BigDecimal getC_tdepc() {
		return c_tdepc;
	}

	public void setC_tdepc(BigDecimal c_tdepc) {
		this.c_tdepc = c_tdepc;
	}

	public BigDecimal getC_tdeps() {
		return c_tdeps;
	}

	public void setC_tdeps(BigDecimal c_tdeps) {
		this.c_tdeps = c_tdeps;
	}

	public BigDecimal getC_ticsha() {
		return c_ticsha;
	}

	public void setC_ticsha(BigDecimal c_ticsha) {
		this.c_ticsha = c_ticsha;
	}

	public BigDecimal getC_tpmt() {
		return c_tpmt;
	}

	public void setC_tpmt(BigDecimal c_tpmt) {
		this.c_tpmt = c_tpmt;
	}

	public BigDecimal getC_tpmtl() {
		return c_tpmtl;
	}

	public void setC_tpmtl(BigDecimal c_tpmtl) {
		this.c_tpmtl = c_tpmtl;
	}

	public BigDecimal getC_tpmtmc() {
		return c_tpmtmc;
	}

	public void setC_tpmtmc(BigDecimal c_tpmtmc) {
		this.c_tpmtmc = c_tpmtmc;
	}

	public BigDecimal getC_tsvcload() {
		return c_tsvcload;
	}

	public void setC_tsvcload(BigDecimal c_tsvcload) {
		this.c_tsvcload = c_tsvcload;
	}

	public BigDecimal getC_tsvcunload() {
		return c_tsvcunload;
	}

	public void setC_tsvcunload(BigDecimal c_tsvcunload) {
		this.c_tsvcunload = c_tsvcunload;
	}

	public BigDecimal getC_twdl_loc() {
		return c_twdl_loc;
	}

	public void setC_twdl_loc(BigDecimal c_twdl_loc) {
		this.c_twdl_loc = c_twdl_loc;
	}

	public BigDecimal getC_twdl_us() {
		return c_twdl_us;
	}

	public void setC_twdl_us(BigDecimal c_twdl_us) {
		this.c_twdl_us = c_twdl_us;
	}

	public BigDecimal getC_twdlc_loc() {
		return c_twdlc_loc;
	}

	public void setC_twdlc_loc(BigDecimal c_twdlc_loc) {
		this.c_twdlc_loc = c_twdlc_loc;
	}

	public BigDecimal getC_twdlc_us() {
		return c_twdlc_us;
	}

	public void setC_twdlc_us(BigDecimal c_twdlc_us) {
		this.c_twdlc_us = c_twdlc_us;
	}

	public BigDecimal getC_twdls_loc() {
		return c_twdls_loc;
	}

	public void setC_twdls_loc(BigDecimal c_twdls_loc) {
		this.c_twdls_loc = c_twdls_loc;
	}

	public BigDecimal getC_twdls_us() {
		return c_twdls_us;
	}

	public void setC_twdls_us(BigDecimal c_twdls_us) {
		this.c_twdls_us = c_twdls_us;
	}

	public BigDecimal getC1_start_bills() {
		return c1_start_bills;
	}

	public void setC1_start_bills(BigDecimal c1_start_bills) {
		this.c1_start_bills = c1_start_bills;
	}

	public BigDecimal getC2_start_bills() {
		return c2_start_bills;
	}

	public void setC2_start_bills(BigDecimal c2_start_bills) {
		this.c2_start_bills = c2_start_bills;
	}

	public BigDecimal getC3_start_bills() {
		return c3_start_bills;
	}

	public void setC3_start_bills(BigDecimal c3_start_bills) {
		this.c3_start_bills = c3_start_bills;
	}

	public BigDecimal getC4_start_bills() {
		return c4_start_bills;
	}

	public void setC4_start_bills(BigDecimal c4_start_bills) {
		this.c4_start_bills = c4_start_bills;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}