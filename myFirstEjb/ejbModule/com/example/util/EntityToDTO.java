package com.example.util;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.dao.CenterDAO;
import com.example.dao.CenterDAORemote;
import com.example.dto.AppointmentDTO;
import com.example.dto.CenterDTO;
import com.example.dto.UserDTO;

import model.Appointment;
import model.Center;
import model.User;

public class EntityToDTO {	
	
	public UserDTO convertUser(User user) {	
		UserDTO globalUserDTO = new UserDTO(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), 
				user.getIdentitycardCnp(), user.getGender(), user.getRole());
		
		globalUserDTO.setId(user.getIduser());
		return globalUserDTO;

	}

	public CenterDTO convertCenter(Center center) {
		CenterDTO globalCenterDTO = new CenterDTO(center.getIdcenter(), center.getAddress(), center.getCenterCity(), center.getCenterName(), 
				center.getDisponibilityPerDay(), center.getPhone());
		
		globalCenterDTO.setIdcenter(center.getIdcenter());
		return globalCenterDTO;
	}
		
	private static String fromDBStatus(int status) {
	    switch (status) {
	    case 0:
	        return "pending";

	    case 1:
	         return "accepted";

	    case 2:
	         return "rejected";

	    default:
	        throw new IllegalArgumentException("ShortValue [" + status + "] not supported.");
	    }
	}
	
	private static String fromDBType(int type) {
	    switch (type) {
	    case 0:
	         return "covid-19 test";

	    case 1:
	         return"covid-19 vaccine";
	         
	    case 2:
	         return"health control";

	    default:
	        throw new IllegalArgumentException("ShortValue [" + type + "] not supported.");
	    }
	}
	
	
	public AppointmentDTO convertAppointment(Appointment appointment) {	
		AppointmentDTO globalAppointmentDTO = new AppointmentDTO(
							appointment.getIdappointment(), 
							appointment.getIduser(), 
							appointment.getIdcenter(),
							fromDBType(appointment.getType()),
							appointment.getCreationDate(), 
							appointment.getAppointmentDate(), 
							appointment.getLastUpdate(), 
							fromDBStatus(appointment.getStatus()));
		
		globalAppointmentDTO.setIdappointment(appointment.getIdappointment());
		return globalAppointmentDTO;
	}
}
