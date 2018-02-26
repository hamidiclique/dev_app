package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the T_FUNGRP_FUN_MAP database table.
 * 
 */
@Entity
@Table(name="T_FUNGRP_FUN_MAP")
//@NamedQuery(name="TFungrpFunMap.findAll", query="SELECT t FROM TFungrpFunMap t")
public class FungrpFunMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FungrpFunMapPK id;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	@Column(name="SYS_FLAG")
	private String sysFlag;

	public FungrpFunMap() {
	}

	public FungrpFunMapPK getId() {
		return this.id;
	}

	public void setId(FungrpFunMapPK id) {
		this.id = id;
	}

	public String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSysFlag() {
		return this.sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

}