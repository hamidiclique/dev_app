package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the T_FUNCTIONGRP database table.
 * 
 */
@Entity
@Table(name="T_FUNCTIONGRP")
@NamedQuery(name="TFunctiongrp.findAll", query="SELECT t FROM TFunctiongrp t")
public class TFunctiongrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FUNCTIONGRP_ID")
	private String functiongrpId;

	@Column(name="FUNCTIONGRP_DESC")
	private String functiongrpDesc;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	public TFunctiongrp() {
	}

	public String getFunctiongrpId() {
		return this.functiongrpId;
	}

	public void setFunctiongrpId(String functiongrpId) {
		this.functiongrpId = functiongrpId;
	}

	public String getFunctiongrpDesc() {
		return this.functiongrpDesc;
	}

	public void setFunctiongrpDesc(String functiongrpDesc) {
		this.functiongrpDesc = functiongrpDesc;
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

}