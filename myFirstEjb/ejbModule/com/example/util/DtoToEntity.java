package com.example.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.dto.AppointmentDTO;
import com.example.dto.CenterDTO;
import com.example.dto.RegisterDTO;
import com.example.dto.UserDTO;

import model.Appointment;
import model.Center;
import model.User;

public class DtoToEntity {

	// all classes doesn't take primary key in account

	public User convertUser(UserDTO userDTO) {
		User user = new User(userDTO.getUsername(), userDTO.getPassword());

		return user;
	}
	
	public User convertUserRegister(RegisterDTO registerDTO) {	
	
		User user = new User(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getFirstName(), registerDTO.getLastName(), 
				registerDTO.getDateOfBirth(), registerDTO.getIdentityCardNumber(), registerDTO.getGender());
		return user;
	}

	public Center convertCenter(CenterDTO centerDTO) {
		Center center = new Center(centerDTO.getIdcenter(), centerDTO.getAddress(), centerDTO.getCenterCity(), centerDTO.getCenterName(), 
				centerDTO.getDisponibilityPerDay(), centerDTO.getPhone());
		return center;
	}

	public static int fromEntityStatus(String status) {
	    switch (status) {
	    case "pending":
	         return 0;

	    case "accepted":
	         return 1;

	    case "rejected":
	         return 2;

	    default:
	        return 0;
	    }
	}
	
	public static int fromEntityType(String type) {
	    switch (type) {
	    case "1":
	         return 0;

	    case "2":
	         return 1;

	    case "3":
	         return 2;

	    default:
	        throw new IllegalArgumentException("ShortValue [" + type + "] not supported.");
	    }
	}
	
	
	public Appointment convertAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = new Appointment(
					appointmentDTO.getIdappointment(), 
					appointmentDTO.getIduser(), 
					appointmentDTO.getCenterID(), 
					fromEntityType(appointmentDTO.getType()), 
					appointmentDTO.getCreationDate(),
					appointmentDTO.getAppointmentDate(), 
					appointmentDTO.getLastUpdate(), 
					fromEntityStatus(appointmentDTO.getStatus()));
		
		return appointment;
	}

}
