package com.example.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AppointmentDTO {
	
	private int idappointment;
	private Date appointmentDate;
	private Date creationDate;
	private int centerID;
	private int iduser;
	private Date lastUpdate;
	private String status;
	private String type;
	
	public AppointmentDTO() {
		super();
	}

	public AppointmentDTO(int idappointment, int iduser, int centerID, String type, Date creationDate, Date appointmentDate, 
			Date lastUpdate, String status) {
		super();
		this.idappointment = idappointment;
		this.iduser = iduser;
		this.centerID = centerID;
		this.type = type;
		this.creationDate = creationDate;
		this.appointmentDate = appointmentDate;
		this.lastUpdate = lastUpdate;
		this.status = status;
	}

	public int getIdappointment() {
		return idappointment;
	}

	public void setIdappointment(int idappointment) {
		this.idappointment = idappointment;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public int getCenterID() {
		return centerID;
	}

	public void setCenterID(int centerID) {
		this.centerID = centerID;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
