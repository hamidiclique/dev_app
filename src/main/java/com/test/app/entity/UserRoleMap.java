package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the T_USER_ROLE_MAP database table.
 * 
 */
@Entity
@Table(name="T_USER_ROLE_MAP")
//@NamedQuery(name="TUserRoleMap.findAll", query="SELECT t FROM TUserRoleMap t")
public class UserRoleMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleMapPK id;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	@Column(name="SYS_FLAG")
	private String sysFlag;

	public UserRoleMap() {
	}

	public UserRoleMapPK getId() {
		return this.id;
	}

	public void setId(UserRoleMapPK id) {
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