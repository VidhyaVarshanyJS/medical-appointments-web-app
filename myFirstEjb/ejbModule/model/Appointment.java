package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the appointment database table.
 * 
 */
@Entity
@Table(name="appointment")
@NamedQuery(name="findAllAppointments", query="SELECT a FROM Appointment a ORDER BY a.idappointment DESC")
@NamedQuery(name="findAllAppointmentsByUser", query="SELECT a FROM Appointment a WHERE a.iduser = :iduser ORDER BY a.idappointment DESC")
@NamedQuery(name="findPendingAppointmentsByUser", query="SELECT a FROM Appointment a WHERE a.iduser = :iduser AND a.status = 0 ORDER BY a.idappointment DESC")
@NamedQuery(name="findUserFutureAppointments", query="SELECT a FROM Appointment a WHERE a.iduser = :iduser AND a.status = 1 AND "
		+ "a.appointmentDate >= :nowDate ORDER BY a.idappointment DESC")

public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idappointment;

	@Temporal(TemporalType.DATE)
	@Column(name="appointment_date")
	private Date appointmentDate;

	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	private Date creationDate;

	private int idcenter;

	private int iduser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;

	private int status;

	private int type;

	public Appointment() {
	}

	public Appointment(int idappointment, int iduser, int idcenter, int type, Date creationDate,
			Date appointmentDate, Date lastUpdate, int status) {
		super();
		this.idappointment = idappointment;
		this.iduser = iduser;
		this.idcenter = idcenter;
		this.type = type;
		this.creationDate = creationDate;
		this.appointmentDate = appointmentDate;
		this.lastUpdate = lastUpdate;
		this.status = status;
	}


	public int getIdappointment() {
		return this.idappointment;
	}

	public void setIdappointment(int idappointment) {
		this.idappointment = idappointment;
	}

	public Date getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getIdcenter() {
		return this.idcenter;
	}

	public void setIdcenter(int idcenter) {
		this.idcenter = idcenter;
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}