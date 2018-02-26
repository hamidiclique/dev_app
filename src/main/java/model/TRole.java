package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the T_ROLE database table.
 * 
 */
@Entity
@Table(name="T_ROLE")
@NamedQuery(name="TRole.findAll", query="SELECT t FROM TRole t")
public class TRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_ID")
	private String roleId;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	@Column(name="ROLE_DESC")
	private String roleDesc;

	public TRole() {
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}