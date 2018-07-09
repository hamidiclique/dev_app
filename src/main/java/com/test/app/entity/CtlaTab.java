package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the CTLA_TAB database table.
 * 
 */
@Entity
@Table(name = "CTLA_TAB")
// @NamedQuery(name="CtlaTab.findAll", query="SELECT c FROM CtlaTab c")
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

	@Column(name = "MEM_NO")
	private BigDecimal mem_no;

	@Column(name = "MIS_FLAG1")
	private BigDecimal mis_flag1;

	@Column(name = "MIS_FLAG2")
	private BigDecimal mis_flag2;

	@Column(name = "MIS_FLD1")
	private BigDecimal mis_fld1;

	@Column(name = "MIS_FLD2")
	private BigDecimal mis_fld2;

	private BigDecimal miserr;

	@Column(name = "MSG_COORD_NUM")
	private String msg_coord_num;

	@Column(name = "NON_READIES")
	private BigDecimal non_readies;

	private BigDecimal ntrans;

	private BigDecimal oprob;

	private BigDecimal opstat;

	private BigDecimal outctr;

	private BigDecimal outtot;

	private BigDecimal procstat;

	private BigDecimal rencon;

	private BigDecimal respno;

	@Column(name = "STAT1_1")
	private BigDecimal stat1_1;

	@Column(name = "STAT1_10")
	private BigDecimal stat1_10;

	@Column(name = "STAT1_2")
	private BigDecimal stat1_2;

	@Column(name = "STAT1_3")
	private BigDecimal stat1_3;

	@Column(name = "STAT1_4")
	private BigDecimal stat1_4;

	@Column(name = "STAT1_5")
	private BigDecimal stat1_5;

	@Column(name = "STAT1_6")
	private BigDecimal stat1_6;

	@Column(name = "STAT1_7")
	private BigDecimal stat1_7;

	@Column(name = "STAT1_8")
	private BigDecimal stat1_8;

	@Column(name = "STAT1_9")
	private BigDecimal stat1_9;

	@Column(name = "STAT2_1")
	private BigDecimal stat2_1;

	@Column(name = "STAT2_2")
	private BigDecimal stat2_2;

	@Column(name = "STATUS_MSG")
	private String status_msg;

	@Column(name = "SW_CONFIG")
	private BigDecimal sw_config;

	@Column(name = "SW_EKEY")
	private BigDecimal sw_ekey;

	@Column(name = "SW_LSKEY")
	private BigDecimal sw_lskey;

	@Column(name = "SW_REQ")
	private BigDecimal sw_req;

	@Column(name = "SW_SKEY")
	private BigDecimal sw_skey;

	@Column(name = "SW_TAB")
	private BigDecimal sw_tab;

	private BigDecimal tto;

	private BigDecimal ttype;

	private BigDecimal warn1;

	private BigDecimal warn2;

	public CtlaTab() {
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public BigDecimal getAcct1tp() {
		return acct1tp;
	}

	public void setAcct1tp(BigDecimal acct1tp) {
		this.acct1tp = acct1tp;
	}

	public BigDecimal getAcct2tp() {
		return acct2tp;
	}

	public void setAcct2tp(BigDecimal acct2tp) {
		this.acct2tp = acct2tp;
	}

	public BigDecimal getAreject() {
		return areject;
	}

	public void setAreject(BigDecimal areject) {
		this.areject = areject;
	}

	public BigDecimal getClosed() {
		return closed;
	}

	public void setClosed(BigDecimal closed) {
		this.closed = closed;
	}

	public BigDecimal getCmdwt() {
		return cmdwt;
	}

	public void setCmdwt(BigDecimal cmdwt) {
		this.cmdwt = cmdwt;
	}

	public BigDecimal getCtype() {
		return ctype;
	}

	public void setCtype(BigDecimal ctype) {
		this.ctype = ctype;
	}

	public BigDecimal getDisab1() {
		return disab1;
	}

	public void setDisab1(BigDecimal disab1) {
		this.disab1 = disab1;
	}

	public BigDecimal getDisab2() {
		return disab2;
	}

	public void setDisab2(BigDecimal disab2) {
		this.disab2 = disab2;
	}

	public BigDecimal getHcmore() {
		return hcmore;
	}

	public void setHcmore(BigDecimal hcmore) {
		this.hcmore = hcmore;
	}

	public BigDecimal getHcsent() {
		return hcsent;
	}

	public void setHcsent(BigDecimal hcsent) {
		this.hcsent = hcsent;
	}

	public BigDecimal getHcstart() {
		return hcstart;
	}

	public void setHcstart(BigDecimal hcstart) {
		this.hcstart = hcstart;
	}

	public BigDecimal getHreject() {
		return hreject;
	}

	public void setHreject(BigDecimal hreject) {
		this.hreject = hreject;
	}

	public String getLacc2() {
		return lacc2;
	}

	public void setLacc2(String lacc2) {
		this.lacc2 = lacc2;
	}

	public String getLacci() {
		return lacci;
	}

	public void setLacci(String lacci) {
		this.lacci = lacci;
	}

	public BigDecimal getLamt() {
		return lamt;
	}

	public void setLamt(BigDecimal lamt) {
		this.lamt = lamt;
	}

	public BigDecimal getLauth() {
		return lauth;
	}

	public void setLauth(BigDecimal lauth) {
		this.lauth = lauth;
	}

	public BigDecimal getLcardtype() {
		return lcardtype;
	}

	public void setLcardtype(BigDecimal lcardtype) {
		this.lcardtype = lcardtype;
	}

	public BigDecimal getLdisp() {
		return ldisp;
	}

	public void setLdisp(BigDecimal ldisp) {
		this.ldisp = ldisp;
	}

	public String getLpani() {
		return lpani;
	}

	public void setLpani(String lpani) {
		this.lpani = lpani;
	}

	public BigDecimal getLscreen() {
		return lscreen;
	}

	public void setLscreen(BigDecimal lscreen) {
		this.lscreen = lscreen;
	}

	public BigDecimal getLutrnno() {
		return lutrnno;
	}

	public void setLutrnno(BigDecimal lutrnno) {
		this.lutrnno = lutrnno;
	}

	public BigDecimal getMem_no() {
		return mem_no;
	}

	public void setMem_no(BigDecimal mem_no) {
		this.mem_no = mem_no;
	}

	public BigDecimal getMis_flag1() {
		return mis_flag1;
	}

	public void setMis_flag1(BigDecimal mis_flag1) {
		this.mis_flag1 = mis_flag1;
	}

	public BigDecimal getMis_flag2() {
		return mis_flag2;
	}

	public void setMis_flag2(BigDecimal mis_flag2) {
		this.mis_flag2 = mis_flag2;
	}

	public BigDecimal getMis_fld1() {
		return mis_fld1;
	}

	public void setMis_fld1(BigDecimal mis_fld1) {
		this.mis_fld1 = mis_fld1;
	}

	public BigDecimal getMis_fld2() {
		return mis_fld2;
	}

	public void setMis_fld2(BigDecimal mis_fld2) {
		this.mis_fld2 = mis_fld2;
	}

	public BigDecimal getMiserr() {
		return miserr;
	}

	public void setMiserr(BigDecimal miserr) {
		this.miserr = miserr;
	}

	public String getMsg_coord_num() {
		return msg_coord_num;
	}

	public void setMsg_coord_num(String msg_coord_num) {
		this.msg_coord_num = msg_coord_num;
	}

	public BigDecimal getNon_readies() {
		return non_readies;
	}

	public void setNon_readies(BigDecimal non_readies) {
		this.non_readies = non_readies;
	}

	public BigDecimal getNtrans() {
		return ntrans;
	}

	public void setNtrans(BigDecimal ntrans) {
		this.ntrans = ntrans;
	}

	public BigDecimal getOprob() {
		return oprob;
	}

	public void setOprob(BigDecimal oprob) {
		this.oprob = oprob;
	}

	public BigDecimal getOpstat() {
		return opstat;
	}

	public void setOpstat(BigDecimal opstat) {
		this.opstat = opstat;
	}

	public BigDecimal getOutctr() {
		return outctr;
	}

	public void setOutctr(BigDecimal outctr) {
		this.outctr = outctr;
	}

	public BigDecimal getOuttot() {
		return outtot;
	}

	public void setOuttot(BigDecimal outtot) {
		this.outtot = outtot;
	}

	public BigDecimal getProcstat() {
		return procstat;
	}

	public void setProcstat(BigDecimal procstat) {
		this.procstat = procstat;
	}

	public BigDecimal getRencon() {
		return rencon;
	}

	public void setRencon(BigDecimal rencon) {
		this.rencon = rencon;
	}

	public BigDecimal getRespno() {
		return respno;
	}

	public void setRespno(BigDecimal respno) {
		this.respno = respno;
	}

	public BigDecimal getStat1_1() {
		return stat1_1;
	}

	public void setStat1_1(BigDecimal stat1_1) {
		this.stat1_1 = stat1_1;
	}

	public BigDecimal getStat1_10() {
		return stat1_10;
	}

	public void setStat1_10(BigDecimal stat1_10) {
		this.stat1_10 = stat1_10;
	}

	public BigDecimal getStat1_2() {
		return stat1_2;
	}

	public void setStat1_2(BigDecimal stat1_2) {
		this.stat1_2 = stat1_2;
	}

	public BigDecimal getStat1_3() {
		return stat1_3;
	}

	public void setStat1_3(BigDecimal stat1_3) {
		this.stat1_3 = stat1_3;
	}

	public BigDecimal getStat1_4() {
		return stat1_4;
	}

	public void setStat1_4(BigDecimal stat1_4) {
		this.stat1_4 = stat1_4;
	}

	public BigDecimal getStat1_5() {
		return stat1_5;
	}

	public void setStat1_5(BigDecimal stat1_5) {
		this.stat1_5 = stat1_5;
	}

	public BigDecimal getStat1_6() {
		return stat1_6;
	}

	public void setStat1_6(BigDecimal stat1_6) {
		this.stat1_6 = stat1_6;
	}

	public BigDecimal getStat1_7() {
		return stat1_7;
	}

	public void setStat1_7(BigDecimal stat1_7) {
		this.stat1_7 = stat1_7;
	}

	public BigDecimal getStat1_8() {
		return stat1_8;
	}

	public void setStat1_8(BigDecimal stat1_8) {
		this.stat1_8 = stat1_8;
	}

	public BigDecimal getStat1_9() {
		return stat1_9;
	}

	public void setStat1_9(BigDecimal stat1_9) {
		this.stat1_9 = stat1_9;
	}

	public BigDecimal getStat2_1() {
		return stat2_1;
	}

	public void setStat2_1(BigDecimal stat2_1) {
		this.stat2_1 = stat2_1;
	}

	public BigDecimal getStat2_2() {
		return stat2_2;
	}

	public void setStat2_2(BigDecimal stat2_2) {
		this.stat2_2 = stat2_2;
	}

	public String getStatus_msg() {
		return status_msg;
	}

	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}

	public BigDecimal getSw_config() {
		return sw_config;
	}

	public void setSw_config(BigDecimal sw_config) {
		this.sw_config = sw_config;
	}

	public BigDecimal getSw_ekey() {
		return sw_ekey;
	}

	public void setSw_ekey(BigDecimal sw_ekey) {
		this.sw_ekey = sw_ekey;
	}

	public BigDecimal getSw_lskey() {
		return sw_lskey;
	}

	public void setSw_lskey(BigDecimal sw_lskey) {
		this.sw_lskey = sw_lskey;
	}

	public BigDecimal getSw_req() {
		return sw_req;
	}

	public void setSw_req(BigDecimal sw_req) {
		this.sw_req = sw_req;
	}

	public BigDecimal getSw_skey() {
		return sw_skey;
	}

	public void setSw_skey(BigDecimal sw_skey) {
		this.sw_skey = sw_skey;
	}

	public BigDecimal getSw_tab() {
		return sw_tab;
	}

	public void setSw_tab(BigDecimal sw_tab) {
		this.sw_tab = sw_tab;
	}

	public BigDecimal getTto() {
		return tto;
	}

	public void setTto(BigDecimal tto) {
		this.tto = tto;
	}

	public BigDecimal getTtype() {
		return ttype;
	}

	public void setTtype(BigDecimal ttype) {
		this.ttype = ttype;
	}

	public BigDecimal getWarn1() {
		return warn1;
	}

	public void setWarn1(BigDecimal warn1) {
		this.warn1 = warn1;
	}

	public BigDecimal getWarn2() {
		return warn2;
	}

	public void setWarn2(BigDecimal warn2) {
		this.warn2 = warn2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}