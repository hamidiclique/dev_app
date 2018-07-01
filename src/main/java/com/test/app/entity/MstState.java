package com.test.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_STATE database table.
 * 
 */
@Entity
@Table(name="MST_STATE")
//@NamedQuery(name="MstState.findAll", query="SELECT m FROM MstState m")
public class MstState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String circuit;

	private String name;

	public MstState() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCircuit() {
		return this.circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}