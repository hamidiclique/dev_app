package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_BRANCH database table.
 * 
 */
@Entity
@Table(name="MST_BRANCH")
//@NamedQuery(name="MstBranch.findAll", query="SELECT m FROM MstBranch m")
public class MstBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BRANCH_ID")
	private long branchId;

	@Column(name="BRANCH_NAME")
	private String branchName;

	public MstBranch() {
	}

	public long getBranchId() {
		return this.branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}