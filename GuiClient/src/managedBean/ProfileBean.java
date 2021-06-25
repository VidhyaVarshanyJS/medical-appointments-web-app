package managedBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.dao.UserDAORemote;
import com.example.dto.UserDTO;
import com.example.exception.RegisterException;
import com.example.exception.UpdateProfileException;

@Named(value = "profileBean")
@ManagedBean
@RequestScoped
public class ProfileBean {


	@EJB
	UserDAORemote userDAORemote;

	UserDTO userDTO;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext.getExternalContext().getSessionMap().get("userDTO") != null) {
			UserDTO tempUserDTO = (UserDTO) facesContext.getExternalContext().getSessionMap().get("userDTO");
			userDTO = userDAORemote.getUserProfile(tempUserDTO.getUsername());
		}
	}

	public String editUserProfile() throws UpdateProfileException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try {
			userDAORemote.editUserProfile(userDTO);
			
			facesContext.addMessage("registerForm",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile updated succesfully!", null));
			return "success";
		} catch (RegisterException e) {
			System.out.println("Invalid format.");
			facesContext.addMessage("registerForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			return null;
		}
		
	}
	
	public UserDAORemote getUserDAORemote() {
		return userDAORemote;
	}

	public void setUserDAORemote(UserDAORemote userDAORemote) {
		this.userDAORemote = userDAORemote;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}