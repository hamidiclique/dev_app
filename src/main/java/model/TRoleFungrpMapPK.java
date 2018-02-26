package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the T_ROLE_FUNGRP_MAP database table.
 * 
 */
@Embeddable
public class TRoleFungrpMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_ID")
	private String roleId;

	@Column(name="FUNCTIONGRP_ID")
	private String functiongrpId;

	public TRoleFungrpMapPK() {
	}
	public String getRoleId() {
		return this.roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getFunctiongrpId() {
		return this.functiongrpId;
	}
	public void setFunctiongrpId(String functiongrpId) {
		this.functiongrpId = functiongrpId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TRoleFungrpMapPK)) {
			return false;
		}
		TRoleFungrpMapPK castOther = (TRoleFungrpMapPK)other;
		return 
			this.roleId.equals(castOther.roleId)
			&& this.functiongrpId.equals(castOther.functiongrpId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId.hashCode();
		hash = hash * prime + this.functiongrpId.hashCode();
		
		return hash;
	}
}