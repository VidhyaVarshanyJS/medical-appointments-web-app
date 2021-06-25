package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the centers database table.
 * 
 */
@Entity
@Table(name="centers")
@NamedQuery(name="findAll", query="SELECT c FROM Center c")
@NamedQuery(name="findCenterByID", query="SELECT c FROM Center c WHERE c.idcenter = :centerID")
@NamedQuery(name="findCenterByName", query="SELECT c FROM Center c WHERE c.centerName = :centerName")
public class Center implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcenter;

	private String address;

	@Column(name="center_city")
	private String centerCity;

	@Column(name="center_name")
	private String centerName;

	@Column(name="disponibility_per_day")
	private int disponibilityPerDay;

	private String phone;

	public Center() {
	}

	public Center(int idcenter, String address, String centerCity, String centerName, int disponibilityPerDay, String phone) {
		super();
		this.idcenter = idcenter;
		this.address = address;
		this.centerCity = centerCity;
		this.centerName = centerName;
		this.disponibilityPerDay = disponibilityPerDay;
		this.phone = phone;
	}

	public int getIdcenter() {
		return this.idcenter;
	}

	public void setIdcenter(int idcenter) {
		this.idcenter = idcenter;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCenterCity() {
		return this.centerCity;
	}

	public void setCenterCity(String centerCity) {
		this.centerCity = centerCity;
	}

	public String getCenterName() {
		return this.centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public int getDisponibilityPerDay() {
		return this.disponibilityPerDay;
	}

	public void setDisponibilityPerDay(int disponibilityPerDay) {
		this.disponibilityPerDay = disponibilityPerDay;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}