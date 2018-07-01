package com.test.app.dto;

import java.util.List;

import com.test.app.entity.AtmCurrencyTab;
import com.test.app.entity.CtimTab;
import com.test.app.entity.CtlaTab;
import com.test.app.entity.CtrTab;
import com.test.app.entity.DefTab;
import com.test.app.entity.DevAttribute;
import com.test.app.entity.DevKeyTab;
import com.test.app.entity.EcfopTab;
import com.test.app.entity.RtTab;
import com.test.app.entity.TcpTab;
import com.test.app.entity.TmkCompTab;
import com.test.app.entity.TmkReqTab;

public class AtmMaster {

	private RtTab rttab;
	private TcpTab tcptab;
	private DefTab deftab;
	private CtlaTab ctlatab;
	private CtrTab ctrtab;
	private List<CtimTab> ctimtablist;
	private List<AtmCurrencyTab> atmcurrtablist;
	private List<EcfopTab> ecfoptablist;
	private DevAttribute devattr;
	private DevKeyTab devkeytab;
	private TmkCompTab tmkcomptab;
	//private List<TmkCompTab> tmkcomptablist;
	private TmkReqTab tmkreqtab;

	public RtTab getRttab() {
		return rttab;
	}

	public void setRttab(RtTab rttab) {
		this.rttab = rttab;
	}

	public TcpTab getTcptab() {
		return tcptab;
	}

	public void setTcptab(TcpTab tcptab) {
		this.tcptab = tcptab;
	}

	public DefTab getDeftab() {
		return deftab;
	}

	public void setDeftab(DefTab deftab) {
		this.deftab = deftab;
	}

	public CtlaTab getCtlatab() {
		return ctlatab;
	}

	public void setCtlatab(CtlaTab ctlatab) {
		this.ctlatab = ctlatab;
	}

	public CtrTab getCtrtab() {
		return ctrtab;
	}

	public void setCtrtab(CtrTab ctrtab) {
		this.ctrtab = ctrtab;
	}

	/*
	 * public CtimTab getCtimtab() { return ctimtab; }
	 * 
	 * public void setCtimtab(CtimTab ctimtab) { this.ctimtab = ctimtab; }
	 * 
	 * public AtmCurrencyTab getAtmcurrtab() { return atmcurrtab; }
	 * 
	 * public void setAtmcurrtab(AtmCurrencyTab atmcurrtab) { this.atmcurrtab =
	 * atmcurrtab; }
	 * 
	 * public EcfopTab getEcfoptab() { return ecfoptab; }
	 * 
	 * public void setEcfoptab(EcfopTab ecfoptab) { this.ecfoptab = ecfoptab; }
	 */

	public DevAttribute getDevattr() {
		return devattr;
	}

	public void setDevattr(DevAttribute devattr) {
		this.devattr = devattr;
	}

	public DevKeyTab getDevkeytab() {
		return devkeytab;
	}

	public void setDevkeytab(DevKeyTab devkeytab) {
		this.devkeytab = devkeytab;
	}

	public TmkCompTab getTmkcomptab() {
		return tmkcomptab;
	}

	public void setTmkcomptab(TmkCompTab tmkcomptab) {
		this.tmkcomptab = tmkcomptab;
	}

	public TmkReqTab getTmkreqtab() {
		return tmkreqtab;
	}

	public void setTmkreqtab(TmkReqTab tmkreqtab) {
		this.tmkreqtab = tmkreqtab;
	}

	public List<CtimTab> getCtimtablist() {
		return ctimtablist;
	}

	public void setCtimtablist(List<CtimTab> ctimtablist) {
		this.ctimtablist = ctimtablist;
	}

	public List<AtmCurrencyTab> getAtmcurrtablist() {
		return atmcurrtablist;
	}

	public void setAtmcurrtablist(List<AtmCurrencyTab> atmcurrtablist) {
		this.atmcurrtablist = atmcurrtablist;
	}

	public List<EcfopTab> getEcfoptablist() {
		return ecfoptablist;
	}

	public void setEcfoptablist(List<EcfopTab> ecfoptablist) {
		this.ecfoptablist = ecfoptablist;
	}

/*	public List<TmkCompTab> getTmkcomptablist() {
		return tmkcomptablist;
	}

	public void setTmkcomptablist(List<TmkCompTab> tmkcomptablist) {
		this.tmkcomptablist = tmkcomptablist;
	}*/

}
