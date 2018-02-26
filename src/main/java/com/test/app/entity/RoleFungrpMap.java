package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the T_ROLE_FUNGRP_MAP database table.
 * 
 */
@Entity
@Table(name="T_ROLE_FUNGRP_MAP")
//@NamedQuery(name="TRoleFungrpMap.findAll", query="SELECT t FROM TRoleFungrpMap t")
public class RoleFungrpMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleFungrpMapPK id;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	@Column(name="SYS_FLAG")
	private String sysFlag;

	public RoleFungrpMap() {
	}

	public RoleFungrpMapPK getId() {
		return this.id;
	}

	public void setId(RoleFungrpMapPK id) {
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