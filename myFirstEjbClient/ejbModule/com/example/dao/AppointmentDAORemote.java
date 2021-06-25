package com.example.dao;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import com.example.dto.AppointmentDTO;
import com.example.dto.CenterDTO;
import com.example.dto.ChangePasswordDTO;
import com.example.dto.LoginDTO;
import com.example.dto.RegisterDTO;
import com.example.dto.UserDTO;
import com.example.exception.ChangePasswordException;
import com.example.exception.CreateAppointmentException;
import com.example.exception.LoginException;
import com.example.exception.RegisterException;
import com.example.exception.UpdateProfileException;
import com.example.exception.UserException;

@Remote
public interface AppointmentDAORemote extends GenericDAO<AppointmentDTO> {

	AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws CreateAppointmentException;

	List<AppointmentDTO> findUserAppointments(int id);
	
	List<AppointmentDTO> findUserPendingAppointments(int id);
	
	List<AppointmentDTO> findUserFutureAppointments(int id);
	
	
}
