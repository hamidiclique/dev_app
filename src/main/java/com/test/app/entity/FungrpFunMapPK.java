package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the T_FUNGRP_FUN_MAP database table.
 * 
 */
@Embeddable
public class FungrpFunMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FUNCTION_ID")
	private String functionId;

	@Column(name="FUNCTIONGRP_ID")
	private String functiongrpId;

	public FungrpFunMapPK() {
	}
	public String getFunctionId() {
		return this.functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
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
		if (!(other instanceof FungrpFunMapPK)) {
			return false;
		}
		FungrpFunMapPK castOther = (FungrpFunMapPK)other;
		return 
			this.functionId.equals(castOther.functionId)
			&& this.functiongrpId.equals(castOther.functiongrpId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.functionId.hashCode();
		hash = hash * prime + this.functiongrpId.hashCode();
		
		return hash;
	}
}