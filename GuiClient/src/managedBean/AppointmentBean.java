package managedBean;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.dao.AppointmentDAORemote;
import com.example.dao.CenterDAORemote;
import com.example.dao.UserDAORemote;
import com.example.dto.AppointmentDTO;
import com.example.dto.CenterDTO;
import com.example.dto.RegisterDTO;
import com.example.dto.UserDTO;
import com.example.exception.CreateAppointmentException;
import com.example.exception.DeleteAppointmentException;
import com.example.exception.RegisterException;

@Named(value = "appointmentBean")
@ManagedBean
@SessionScoped
public class AppointmentBean {
	AppointmentDTO appointmentDTO = new AppointmentDTO();
	
	@EJB
	AppointmentDAORemote appointmentDAORemote;
		
	private List<AppointmentDTO> yoursAppointments;
	private List<AppointmentDTO> userPendingAppointments;
	private List<AppointmentDTO> userFutureAppointments;
	
	//private FacesContext facesContext = FacesContext.getCurrentInstance();
	
	int loggedUserID = -1;
	
	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UserDTO tempUserDTO = (UserDTO) facesContext.getExternalContext().getSessionMap().get("userDTO");
		loggedUserID = tempUserDTO.getId();
	}
	
	public AppointmentDTO getAppointmentDTO() {
		return appointmentDTO;
	}

	public void setAppointmentDTO(AppointmentDTO appointmentDTO) {
		this.appointmentDTO = appointmentDTO;
	}
	
	public String createAppointment() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		appointmentDTO.setCreationDate(new Date());		
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
		appointmentDTO.setLastUpdate(new Date(ts.getTime()));
		//UserDTO tempUserDTO = (UserDTO) facesContext.getExternalContext().getSessionMap().get("userDTO");
		appointmentDTO.setIduser(loggedUserID);
		appointmentDTO.setStatus("pending");
		
		try {
			appointmentDTO = appointmentDAORemote.createAppointment(appointmentDTO);
			
			facesContext.addMessage("createAppointmentForm",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Appointment created succesfully!", null));
			return "success";
		} catch (CreateAppointmentException e) {
			System.out.println("Invalid format.");
			facesContext.addMessage("createAppointmentForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			return null;
		}
	}
	
	public Boolean deleteAppointment(AppointmentDTO appointmentDTO) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			if(loggedUserID != appointmentDTO.getIduser()) {
				throw new DeleteAppointmentException("Appointment is not created by the user who request delete.");
			}
			System.out.println("test - " + appointmentDTO.getIdappointment());
			appointmentDAORemote.delete(appointmentDTO.getIdappointment());					
			
			facesContext.addMessage("pendingAppointmentsList", new FacesMessage(FacesMessage.SEVERITY_INFO, "Appointment deleted succesfully!", null));
			return true;
		} catch (DeleteAppointmentException e) {
			facesContext.addMessage("pendingAppointmentsList", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			return false;
		}
	}
	
	public List<AppointmentDTO> getYoursAppointments() {
		//UserDTO tempUserDTO = (UserDTO) facesContext.getExternalContext().getSessionMap().get("userDTO");
		//int tempUserID = tempUserDTO.getId();
		yoursAppointments = appointmentDAORemote.findUserAppointments(loggedUserID);
		return yoursAppointments;
	}
	
	public List<AppointmentDTO> getuserPendingAppointments() {
		userPendingAppointments = appointmentDAORemote.findUserPendingAppointments(loggedUserID);
		return userPendingAppointments;
	}

	public List<AppointmentDTO> getuserFutureAppointments() {
		userFutureAppointments = appointmentDAORemote.findUserFutureAppointments(loggedUserID);
		return userFutureAppointments;
	}

}