package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the T_SCREEN_FUN_MAP database table.
 * 
 */
@Embeddable
public class TScreenFunMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SCREEN_ID")
	private String screenId;

	@Column(name="BUTTON_DEF")
	private String buttonDef;

	@Column(name="FUNCTION_ID")
	private String functionId;

	public TScreenFunMapPK() {
	}
	public String getScreenId() {
		return this.screenId;
	}
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
	public String getButtonDef() {
		return this.buttonDef;
	}
	public void setButtonDef(String buttonDef) {
		this.buttonDef = buttonDef;
	}
	public String getFunctionId() {
		return this.functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TScreenFunMapPK)) {
			return false;
		}
		TScreenFunMapPK castOther = (TScreenFunMapPK)other;
		return 
			this.screenId.equals(castOther.screenId)
			&& this.buttonDef.equals(castOther.buttonDef)
			&& this.functionId.equals(castOther.functionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.screenId.hashCode();
		hash = hash * prime + this.buttonDef.hashCode();
		hash = hash * prime + this.functionId.hashCode();
		
		return hash;
	}
}