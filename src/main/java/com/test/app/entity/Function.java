package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the T_FUNCTION database table.
 * 
 */
@Entity
@Table(name="T_FUNCTION")
//@NamedQuery(name="TFunction.findAll", query="SELECT t FROM TFunction t")
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FUNCTION_ID")
	private String functionId;

	@Column(name="BATCHRUN_STATUS")
	private String batchrunStatus;

	@Column(name="FUNCTION_DESC")
	private String functionDesc;

	@Column(name="FUNCTION_FLAG")
	private String functionFlag;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	@Column(name="MODULE_ID")
	private String moduleId;

	public Function() {
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getBatchrunStatus() {
		return this.batchrunStatus;
	}

	public void setBatchrunStatus(String batchrunStatus) {
		this.batchrunStatus = batchrunStatus;
	}

	public String getFunctionDesc() {
		return this.functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getFunctionFlag() {
		return this.functionFlag;
	}

	public void setFunctionFlag(String functionFlag) {
		this.functionFlag = functionFlag;
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

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}