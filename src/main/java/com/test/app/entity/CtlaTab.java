package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CTLA_TAB database table.
 * 
 */
@Entity
@Table(name="CTLA_TAB")
//@NamedQuery(name="CtlaTab.findAll", query="SELECT c FROM CtlaTab c")
public class CtlaTab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long pid;

	private BigDecimal acct1tp;

	private BigDecimal acct2tp;

	private BigDecimal areject;

	private BigDecimal closed;

	private BigDecimal cmdwt;

	private BigDecimal ctype;

	private BigDecimal disab1;

	private BigDecimal disab2;

	private BigDecimal hcmore;

	private BigDecimal hcsent;

	private BigDecimal hcstart;

	private BigDecimal hreject;

	private String lacc2;

	private String lacci;

	private BigDecimal lamt;

	private BigDecimal lauth;

	private BigDecimal lcardtype;

	private BigDecimal ldisp;

	private String lpani;

	private BigDecimal lscreen;

	private BigDecimal lutrnno;

	@Column(name="MEM_NO")
	private BigDecimal memNo;

	@Column(name="MIS_FLAG1")
	private BigDecimal misFlag1;

	@Column(name="MIS_FLAG2")
	private BigDecimal misFlag2;

	@Column(name="MIS_FLD1")
	private BigDecimal misFld1;

	@Column(name="MIS_FLD2")
	private BigDecimal misFld2;

	private BigDecimal miserr;

	@Column(name="MSG_COORD_NUM")
	private String msgCoordNum;

	@Column(name="NON_READIES")
	private BigDecimal nonReadies;

	private BigDecimal ntrans;

	private BigDecimal oprob;

	private BigDecimal opstat;

	private BigDecimal outctr;

	private BigDecimal outtot;

	private BigDecimal procstat;

	private BigDecimal rencon;

	private BigDecimal respno;

	@Column(name="STAT1_1")
	private BigDecimal stat11;

	@Column(name="STAT1_10")
	private BigDecimal stat110;

	@Column(name="STAT1_2")
	private BigDecimal stat12;

	@Column(name="STAT1_3")
	private BigDecimal stat13;

	@Column(name="STAT1_4")
	private BigDecimal stat14;

	@Column(name="STAT1_5")
	private BigDecimal stat15;

	@Column(name="STAT1_6")
	private BigDecimal stat16;

	@Column(name="STAT1_7")
	private BigDecimal stat17;

	@Column(name="STAT1_8")
	private BigDecimal stat18;

	@Column(name="STAT1_9")
	private BigDecimal stat19;

	@Column(name="STAT2_1")
	private BigDecimal stat21;

	@Column(name="STAT2_2")
	private BigDecimal stat22;

	@Column(name="STATUS_MSG")
	private String statusMsg;

	@Column(name="SW_CONFIG")
	private BigDecimal swConfig;

	@Column(name="SW_EKEY")
	private BigDecimal swEkey;

	@Column(name="SW_LSKEY")
	private BigDecimal swLskey;

	@Column(name="SW_REQ")
	private BigDecimal swReq;

	@Column(name="SW_SKEY")
	private BigDecimal swSkey;

	@Column(name="SW_TAB")
	private BigDecimal swTab;

	private BigDecimal tto;

	private BigDecimal ttype;

	private BigDecimal warn1;

	private BigDecimal warn2;

	public CtlaTab() {
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getAcct1tp() {
		return this.acct1tp;
	}

	public void setAcct1tp(BigDecimal acct1tp) {
		this.acct1tp = acct1tp;
	}

	public BigDecimal getAcct2tp() {
		return this.acct2tp;
	}

	public void setAcct2tp(BigDecimal acct2tp) {
		this.acct2tp = acct2tp;
	}

	public BigDecimal getAreject() {
		return this.areject;
	}

	public void setAreject(BigDecimal areject) {
		this.areject = areject;
	}

	public BigDecimal getClosed() {
		return this.closed;
	}

	public void setClosed(BigDecimal closed) {
		this.closed = closed;
	}

	public BigDecimal getCmdwt() {
		return this.cmdwt;
	}

	public void setCmdwt(BigDecimal cmdwt) {
		this.cmdwt = cmdwt;
	}

	public BigDecimal getCtype() {
		return this.ctype;
	}

	public void setCtype(BigDecimal ctype) {
		this.ctype = ctype;
	}

	public BigDecimal getDisab1() {
		return this.disab1;
	}

	public void setDisab1(BigDecimal disab1) {
		this.disab1 = disab1;
	}

	public BigDecimal getDisab2() {
		return this.disab2;
	}

	public void setDisab2(BigDecimal disab2) {
		this.disab2 = disab2;
	}

	public BigDecimal getHcmore() {
		return this.hcmore;
	}

	public void setHcmore(BigDecimal hcmore) {
		this.hcmore = hcmore;
	}

	public BigDecimal getHcsent() {
		return this.hcsent;
	}

	public void setHcsent(BigDecimal hcsent) {
		this.hcsent = hcsent;
	}

	public BigDecimal getHcstart() {
		return this.hcstart;
	}

	public void setHcstart(BigDecimal hcstart) {
		this.hcstart = hcstart;
	}

	public BigDecimal getHreject() {
		return this.hreject;
	}

	public void setHreject(BigDecimal hreject) {
		this.hreject = hreject;
	}

	public String getLacc2() {
		return this.lacc2;
	}

	public void setLacc2(String lacc2) {
		this.lacc2 = lacc2;
	}

	public String getLacci() {
		return this.lacci;
	}

	public void setLacci(String lacci) {
		this.lacci = lacci;
	}

	public BigDecimal getLamt() {
		return this.lamt;
	}

	public void setLamt(BigDecimal lamt) {
		this.lamt = lamt;
	}

	public BigDecimal getLauth() {
		return this.lauth;
	}

	public void setLauth(BigDecimal lauth) {
		this.lauth = lauth;
	}

	public BigDecimal getLcardtype() {
		return this.lcardtype;
	}

	public void setLcardtype(BigDecimal lcardtype) {
		this.lcardtype = lcardtype;
	}

	public BigDecimal getLdisp() {
		return this.ldisp;
	}

	public void setLdisp(BigDecimal ldisp) {
		this.ldisp = ldisp;
	}

	public String getLpani() {
		return this.lpani;
	}

	public void setLpani(String lpani) {
		this.lpani = lpani;
	}

	public BigDecimal getLscreen() {
		return this.lscreen;
	}

	public void setLscreen(BigDecimal lscreen) {
		this.lscreen = lscreen;
	}

	public BigDecimal getLutrnno() {
		return this.lutrnno;
	}

	public void setLutrnno(BigDecimal lutrnno) {
		this.lutrnno = lutrnno;
	}

	public BigDecimal getMemNo() {
		return this.memNo;
	}

	public void setMemNo(BigDecimal memNo) {
		this.memNo = memNo;
	}

	public BigDecimal getMisFlag1() {
		return this.misFlag1;
	}

	public void setMisFlag1(BigDecimal misFlag1) {
		this.misFlag1 = misFlag1;
	}

	public BigDecimal getMisFlag2() {
		return this.misFlag2;
	}

	public void setMisFlag2(BigDecimal misFlag2) {
		this.misFlag2 = misFlag2;
	}

	public BigDecimal getMisFld1() {
		return this.misFld1;
	}

	public void setMisFld1(BigDecimal misFld1) {
		this.misFld1 = misFld1;
	}

	public BigDecimal getMisFld2() {
		return this.misFld2;
	}

	public void setMisFld2(BigDecimal misFld2) {
		this.misFld2 = misFld2;
	}

	public BigDecimal getMiserr() {
		return this.miserr;
	}

	public void setMiserr(BigDecimal miserr) {
		this.miserr = miserr;
	}

	public String getMsgCoordNum() {
		return this.msgCoordNum;
	}

	public void setMsgCoordNum(String msgCoordNum) {
		this.msgCoordNum = msgCoordNum;
	}

	public BigDecimal getNonReadies() {
		return this.nonReadies;
	}

	public void setNonReadies(BigDecimal nonReadies) {
		this.nonReadies = nonReadies;
	}

	public BigDecimal getNtrans() {
		return this.ntrans;
	}

	public void setNtrans(BigDecimal ntrans) {
		this.ntrans = ntrans;
	}

	public BigDecimal getOprob() {
		return this.oprob;
	}

	public void setOprob(BigDecimal oprob) {
		this.oprob = oprob;
	}

	public BigDecimal getOpstat() {
		return this.opstat;
	}

	public void setOpstat(BigDecimal opstat) {
		this.opstat = opstat;
	}

	public BigDecimal getOutctr() {
		return this.outctr;
	}

	public void setOutctr(BigDecimal outctr) {
		this.outctr = outctr;
	}

	public BigDecimal getOuttot() {
		return this.outtot;
	}

	public void setOuttot(BigDecimal outtot) {
		this.outtot = outtot;
	}

	public BigDecimal getProcstat() {
		return this.procstat;
	}

	public void setProcstat(BigDecimal procstat) {
		this.procstat = procstat;
	}

	public BigDecimal getRencon() {
		return this.rencon;
	}

	public void setRencon(BigDecimal rencon) {
		this.rencon = rencon;
	}

	public BigDecimal getRespno() {
		return this.respno;
	}

	public void setRespno(BigDecimal respno) {
		this.respno = respno;
	}

	public BigDecimal getStat11() {
		return this.stat11;
	}

	public void setStat11(BigDecimal stat11) {
		this.stat11 = stat11;
	}

	public BigDecimal getStat110() {
		return this.stat110;
	}

	public void setStat110(BigDecimal stat110) {
		this.stat110 = stat110;
	}

	public BigDecimal getStat12() {
		return this.stat12;
	}

	public void setStat12(BigDecimal stat12) {
		this.stat12 = stat12;
	}

	public BigDecimal getStat13() {
		return this.stat13;
	}

	public void setStat13(BigDecimal stat13) {
		this.stat13 = stat13;
	}

	public BigDecimal getStat14() {
		return this.stat14;
	}

	public void setStat14(BigDecimal stat14) {
		this.stat14 = stat14;
	}

	public BigDecimal getStat15() {
		return this.stat15;
	}

	public void setStat15(BigDecimal stat15) {
		this.stat15 = stat15;
	}

	public BigDecimal getStat16() {
		return this.stat16;
	}

	public void setStat16(BigDecimal stat16) {
		this.stat16 = stat16;
	}

	public BigDecimal getStat17() {
		return this.stat17;
	}

	public void setStat17(BigDecimal stat17) {
		this.stat17 = stat17;
	}

	public BigDecimal getStat18() {
		return this.stat18;
	}

	public void setStat18(BigDecimal stat18) {
		this.stat18 = stat18;
	}

	public BigDecimal getStat19() {
		return this.stat19;
	}

	public void setStat19(BigDecimal stat19) {
		this.stat19 = stat19;
	}

	public BigDecimal getStat21() {
		return this.stat21;
	}

	public void setStat21(BigDecimal stat21) {
		this.stat21 = stat21;
	}

	public BigDecimal getStat22() {
		return this.stat22;
	}

	public void setStat22(BigDecimal stat22) {
		this.stat22 = stat22;
	}

	public String getStatusMsg() {
		return this.statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public BigDecimal getSwConfig() {
		return this.swConfig;
	}

	public void setSwConfig(BigDecimal swConfig) {
		this.swConfig = swConfig;
	}

	public BigDecimal getSwEkey() {
		return this.swEkey;
	}

	public void setSwEkey(BigDecimal swEkey) {
		this.swEkey = swEkey;
	}

	public BigDecimal getSwLskey() {
		return this.swLskey;
	}

	public void setSwLskey(BigDecimal swLskey) {
		this.swLskey = swLskey;
	}

	public BigDecimal getSwReq() {
		return this.swReq;
	}

	public void setSwReq(BigDecimal swReq) {
		this.swReq = swReq;
	}

	public BigDecimal getSwSkey() {
		return this.swSkey;
	}

	public void setSwSkey(BigDecimal swSkey) {
		this.swSkey = swSkey;
	}

	public BigDecimal getSwTab() {
		return this.swTab;
	}

	public void setSwTab(BigDecimal swTab) {
		this.swTab = swTab;
	}

	public BigDecimal getTto() {
		return this.tto;
	}

	public void setTto(BigDecimal tto) {
		this.tto = tto;
	}

	public BigDecimal getTtype() {
		return this.ttype;
	}

	public void setTtype(BigDecimal ttype) {
		this.ttype = ttype;
	}

	public BigDecimal getWarn1() {
		return this.warn1;
	}

	public void setWarn1(BigDecimal warn1) {
		this.warn1 = warn1;
	}

	public BigDecimal getWarn2() {
		return this.warn2;
	}

	public void setWarn2(BigDecimal warn2) {
		this.warn2 = warn2;
	}

}