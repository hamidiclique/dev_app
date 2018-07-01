package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TMK_COMP_TAB database table.
 * 
 */
@Embeddable
public class TmkCompTabPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long orgdev;

	@Column(name="\"SEQUENCE\"")
	private long sequence;

	public TmkCompTabPK() {
	}
	public long getOrgdev() {
		return this.orgdev;
	}
	public void setOrgdev(long orgdev) {
		this.orgdev = orgdev;
	}
	public long getSequence() {
		return this.sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TmkCompTabPK)) {
			return false;
		}
		TmkCompTabPK castOther = (TmkCompTabPK)other;
		return 
			(this.orgdev == castOther.orgdev)
			&& (this.sequence == castOther.sequence);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.orgdev ^ (this.orgdev >>> 32)));
		hash = hash * prime + ((int) (this.sequence ^ (this.sequence >>> 32)));
		
		return hash;
	}
}