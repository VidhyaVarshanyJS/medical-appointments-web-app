package managedBean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import com.example.dao.UserDAORemote;
import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.exception.CaptchaException;
import com.example.exception.LoginException;

import util.VerifyRecaptcha;
import util.VerifyCaptcha;

@Named(value = "loginBean")
@ManagedBean
@SessionScoped
public class LoginBean {

	LoginDTO loginDTO = new LoginDTO();
		
	@EJB
	UserDAORemote userDAORemote;

	UserDTO userDTO;

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String loginUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();		 
		String gRecaptchaResponse = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("g-recaptcha-response");	
		
		try {
			String verifier = VerifyCaptcha.verify(gRecaptchaResponse);
			System.out.println("test - " + verifier);
			
			if(verifier != "true") {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("registerForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid captcha / captcha is blank.", null));
				return null;
			}
		} catch (JSONException | IOException e1) {
			e1.printStackTrace();
		}
		
		try {			
			userDTO = userDAORemote.loginUser(loginDTO);
			facesContext.getExternalContext().getSessionMap().put("userDTO", userDTO);
			return "/userFilter/user.xhtml?faces-redirect=true";	
		} catch (LoginException e) {
			facesContext.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			return null;
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		userDTO = null;
		return "/index.xhtml?faces-redirect=true";
	}

}