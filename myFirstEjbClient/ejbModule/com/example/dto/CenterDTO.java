package com.example.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class CenterDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idcenter;
	private String address;
	private String centerCity;
	private String centerName;
	private int disponibilityPerDay;
	private String phone;

	public CenterDTO() {
		super();
	}

	public CenterDTO(int idcenter, String address, String centerCity, String centerName, int disponibilityPerDay,
			String phone) {
		super();
		this.idcenter = idcenter;
		this.address = address;
		this.centerCity = centerCity;
		this.centerName = centerName;
		this.disponibilityPerDay = disponibilityPerDay;
		this.phone = phone;
	}

	public int getIdcenter() {
		return idcenter;
	}

	public void setIdcenter(int idcenter) {
		this.idcenter = idcenter;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCenterCity() {
		return centerCity;
	}

	public void setCenterCity(String centerCity) {
		this.centerCity = centerCity;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public int getDisponibilityPerDay() {
		return disponibilityPerDay;
	}

	public void setDisponibilityPerDay(int disponibilityPerDay) {
		this.disponibilityPerDay = disponibilityPerDay;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "CenterDTO [id=" + idcenter + ", name=" + centerName + "]";
	}

}
