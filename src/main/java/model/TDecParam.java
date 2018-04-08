package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the T_DEC_PARAM database table.
 * 
 */
@Entity
@Table(name="T_DEC_PARAM")
@NamedQuery(name="TDecParam.findAll", query="SELECT t FROM TDecParam t")
public class TDecParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PARAM_ID")
	private String paramId;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	@Column(name="PARAM_DESC")
	private String paramDesc;

	@Column(name="PARAM_VALUE")
	private BigDecimal paramValue;

	public TDecParam() {
	}

	public String getParamId() {
		return this.paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
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

	public String getParamDesc() {
		return this.paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public BigDecimal getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(BigDecimal paramValue) {
		this.paramValue = paramValue;
	}

}