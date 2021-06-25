package com.example.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.dao.UserDAORemote;
import com.example.dto.ChangePasswordDTO;
import com.example.dto.LoginDTO;
import com.example.dto.RegisterDTO;
import com.example.dto.AppointmentDTO;
import com.example.dto.CenterDTO;
import com.example.exception.ChangePasswordException;
import com.example.exception.CreateAppointmentException;
import com.example.exception.LoginException;
import com.example.exception.RegisterException;
import com.example.exception.UpdateProfileException;
import com.example.exception.UserException;
import com.example.util.DtoToEntity;
import com.example.util.EntityToDTO;

import model.Appointment;
import model.Center;
import model.User;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless
@LocalBean
public class AppointmentDAO implements AppointmentDAORemote {

	static final Logger LOGGER = Logger.getLogger(AppointmentDAO.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	public AppointmentDAO() {
	}

	private EntityToDTO entityToDTO = new EntityToDTO();
	private DtoToEntity dtoToEntity = new DtoToEntity();

	@Override
	public AppointmentDTO findById(int id) {
		Appointment appointment = entityManager.find(Appointment.class, id);
		AppointmentDTO appointmentDTO = entityToDTO.convertAppointment(appointment);
		return appointmentDTO;
	}

	@Override
	public List<AppointmentDTO> findAll() {
		List<Appointment> appointments = entityManager.createNamedQuery("findAll", Appointment.class).getResultList();
		System.out.println(appointments.toString());
		List<AppointmentDTO> dtoCenters = new ArrayList<>();
		for(Appointment appointment : appointments) {
			dtoCenters.add(entityToDTO.convertAppointment(appointment));
		}
		return dtoCenters;
	}

	@Override
	public AppointmentDTO create(AppointmentDTO AppointmentDTO) {
		Appointment appointment = dtoToEntity.convertAppointment(AppointmentDTO);
		entityManager.persist(appointment);
		entityManager.flush();
		AppointmentDTO.setIdappointment(appointment.getIdappointment());
		return AppointmentDTO;
	}

	@Override
	public AppointmentDTO update(AppointmentDTO appointmentDTO) {
		Appointment appointment = dtoToEntity.convertAppointment(appointmentDTO);
		appointment.setIdappointment(appointmentDTO.getIdappointment());
		appointment = entityManager.merge(appointment);
		entityManager.flush();
		return appointmentDTO;
	}

	@Override
	public void delete(int id) {
		Appointment appointment = entityManager.find(Appointment.class, id);
		//AppointmentDTO appointmentDTO = findById(id);
		//Appointment appointment = dtoToEntity.convertAppointment(appointmentDTO);
		entityManager.remove(appointment);
		entityManager.flush();
	}

	@Override
	public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws CreateAppointmentException {
		Appointment appointmentEntity = dtoToEntity.convertAppointment(appointmentDTO);
		entityManager.persist(appointmentEntity);
		entityManager.flush();
		appointmentDTO.setIdappointment(appointmentEntity.getIdappointment());
		return appointmentDTO;			
	}

	@Override
	public List<AppointmentDTO> findUserAppointments(int id) {
		List<Appointment> appointments = entityManager.createNamedQuery("findAllAppointmentsByUser", Appointment.class).
					setParameter("iduser", id).getResultList();
		List<AppointmentDTO> userAppointments = new ArrayList<>();
		for(Appointment appointment : appointments) {
			userAppointments.add(entityToDTO.convertAppointment(appointment));
		}
		return userAppointments;
	}
	
	@Override
	public List<AppointmentDTO> findUserPendingAppointments(int id) {
		List<Appointment> appointments = entityManager.createNamedQuery("findPendingAppointmentsByUser", Appointment.class).
					setParameter("iduser", id).getResultList();
		List<AppointmentDTO> userAppointments = new ArrayList<>();
		for(Appointment appointment : appointments) {
			userAppointments.add(entityToDTO.convertAppointment(appointment));
		}
		return userAppointments;
	}

	@Override
	public List<AppointmentDTO> findUserFutureAppointments(int id) {
		List<Appointment> appointments = entityManager.createNamedQuery("findUserFutureAppointments", Appointment.class).setParameter("iduser", id).
				setParameter("nowDate", new Date()).getResultList();
		List<AppointmentDTO> userAppointments = new ArrayList<>();
		for(Appointment appointment : appointments) {
			userAppointments.add(entityToDTO.convertAppointment(appointment));
		}
		return userAppointments;
	}

}
