package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the T_ACTIVE_USERS database table.
 * 
 */
@Entity
@Table(name="T_ACTIVE_USERS")
@NamedQuery(name="TActiveUser.findAll", query="SELECT t FROM TActiveUser t")
public class TActiveUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Column(name="BATCH_RUN_FLAG")
	private String batchRunFlag;

	@Column(name="CONTACT_NUMBER")
	private String contactNumber;

	@Column(name="LAST_ACTIVE_TIME")
	private Timestamp lastActiveTime;

	@Column(name="SCREEN_DESC")
	private String screenDesc;

	@Column(name="SCREEN_ID")
	private String screenId;

	public TActiveUser() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBatchRunFlag() {
		return this.batchRunFlag;
	}

	public void setBatchRunFlag(String batchRunFlag) {
		this.batchRunFlag = batchRunFlag;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Timestamp getLastActiveTime() {
		return this.lastActiveTime;
	}

	public void setLastActiveTime(Timestamp lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public String getScreenDesc() {
		return this.screenDesc;
	}

	public void setScreenDesc(String screenDesc) {
		this.screenDesc = screenDesc;
	}

	public String getScreenId() {
		return this.screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

}