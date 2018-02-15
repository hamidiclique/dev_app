package com.test.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the T_MASTER_HOTEL database table.
 * 
 */
@Entity
@Table(name="T_MASTER_HOTEL")
//@NamedQuery(name="TMasterHotel.findAll", query="SELECT t FROM TMasterHotel t")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOTEL_ID")
	private String hotelId;

	private String city;

	@Column(name="HOTEL_NAME")
	private String hotelName;

	private String status;

	@Column(name="TOTAL_ROOMS")
	private BigDecimal totalRooms;

	public Hotel() {
	}

	public String getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalRooms() {
		return this.totalRooms;
	}

	public void setTotalRooms(BigDecimal totalRooms) {
		this.totalRooms = totalRooms;
	}

}