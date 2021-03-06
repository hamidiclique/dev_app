package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_SCREEN_FUN_MAP database table.
 * 
 */
@Entity
@Table(name="T_SCREEN_FUN_MAP")
//@NamedQuery(name="TScreenFunMap.findAll", query="SELECT t FROM TScreenFunMap t")
public class ScreenFunMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ScreenFunMapPK id;

	@Column(name="ACTLOG_STA")
	private String actlogSta;

	@Column(name="BUTTON_DESC")
	private String buttonDesc;

	@Column(name="RESULT_PAGE")
	private String resultPage;

	public ScreenFunMap() {
	}

	public ScreenFunMapPK getId() {
		return this.id;
	}

	public void setId(ScreenFunMapPK id) {
		this.id = id;
	}

	public String getActlogSta() {
		return this.actlogSta;
	}

	public void setActlogSta(String actlogSta) {
		this.actlogSta = actlogSta;
	}

	public String getButtonDesc() {
		return this.buttonDesc;
	}

	public void setButtonDesc(String buttonDesc) {
		this.buttonDesc = buttonDesc;
	}

	public String getResultPage() {
		return this.resultPage;
	}

	public void setResultPage(String resultPage) {
		this.resultPage = resultPage;
	}

}