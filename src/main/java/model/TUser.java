package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the T_USER database table.
 * 
 */
@Entity
@Table(name="T_USER")
@NamedQuery(name="TUser.findAll", query="SELECT t FROM TUser t")
public class TUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Temporal(TemporalType.DATE)
	@Column(name="ACCOUNT_EXP_DATE")
	private Date accountExpDate;

	@Column(name="CONTACT_NUMBER")
	private String contactNumber;

	private String department;

	private String designation;

	private String email;

	@Column(name="FAIL_COUNT")
	private BigDecimal failCount;

	@Column(name="LAST_LOGIN")
	private Timestamp lastLogin;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="PASSWORD_CRE_DATE")
	private Date passwordCreDate;

	@Temporal(TemporalType.DATE)
	@Column(name="PASSWORD_EXP_DATE")
	private Date passwordExpDate;

	@Column(name="PASSWORD_EXPIRY")
	private String passwordExpiry;

	@Column(name="PRINT_PAN")
	private BigDecimal printPan;

	@Column(name="PRINT_PAN_ONLINE_REP_PROF")
	private String printPanOnlineRepProf;

	@Column(name="STAFF_ID")
	private String staffId;

	private String status;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_ROLE_ID")
	private String userRoleId;

	@Column(name="VIEW_PAN")
	private BigDecimal viewPan;

	@Column(name="VIEW_PAN_FUNC_PROF")
	private String viewPanFuncProf;

	@Column(name="VIEW_SENSITIVE_DATA")
	private String viewSensitiveData;

	public TUser() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getAccountExpDate() {
		return this.accountExpDate;
	}

	public void setAccountExpDate(Date accountExpDate) {
		this.accountExpDate = accountExpDate;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getFailCount() {
		return this.failCount;
	}

	public void setFailCount(BigDecimal failCount) {
		this.failCount = failCount;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPasswordCreDate() {
		return this.passwordCreDate;
	}

	public void setPasswordCreDate(Date passwordCreDate) {
		this.passwordCreDate = passwordCreDate;
	}

	public Date getPasswordExpDate() {
		return this.passwordExpDate;
	}

	public void setPasswordExpDate(Date passwordExpDate) {
		this.passwordExpDate = passwordExpDate;
	}

	public String getPasswordExpiry() {
		return this.passwordExpiry;
	}

	public void setPasswordExpiry(String passwordExpiry) {
		this.passwordExpiry = passwordExpiry;
	}

	public BigDecimal getPrintPan() {
		return this.printPan;
	}

	public void setPrintPan(BigDecimal printPan) {
		this.printPan = printPan;
	}

	public String getPrintPanOnlineRepProf() {
		return this.printPanOnlineRepProf;
	}

	public void setPrintPanOnlineRepProf(String printPanOnlineRepProf) {
		this.printPanOnlineRepProf = printPanOnlineRepProf;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public BigDecimal getViewPan() {
		return this.viewPan;
	}

	public void setViewPan(BigDecimal viewPan) {
		this.viewPan = viewPan;
	}

	public String getViewPanFuncProf() {
		return this.viewPanFuncProf;
	}

	public void setViewPanFuncProf(String viewPanFuncProf) {
		this.viewPanFuncProf = viewPanFuncProf;
	}

	public String getViewSensitiveData() {
		return this.viewSensitiveData;
	}

	public void setViewSensitiveData(String viewSensitiveData) {
		this.viewSensitiveData = viewSensitiveData;
	}

}