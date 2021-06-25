package managedBean;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.json.JSONException;

import com.example.dao.UserDAORemote;
import com.example.dto.RegisterDTO;
import com.example.exception.CaptchaException;
import com.example.exception.RegisterException;

import util.VerifyCaptcha;
import util.VerifyRecaptcha;

@ManagedBean
@SessionScoped
public class RegisterBean {

	RegisterDTO registerDTO = new RegisterDTO();
	
	@EJB
	UserDAORemote userDAORemote;
	
	public RegisterDTO getRegisterDTO() {
		return registerDTO;
	}

	public void setRegisterDTO(RegisterDTO registerDTO) {
		this.registerDTO = registerDTO;
	}
	
	public String registerUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String gRecaptchaResponse = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("g-recaptcha-response");	
		
		try {
			String verifier = VerifyCaptcha.verify(gRecaptchaResponse);
			System.out.println("test - " + verifier);
			
			if(verifier != "true") {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("registerForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Captcha is not resolved. Please recheck.", null));
				return null;
			}
		} catch (JSONException | IOException e1) {
			e1.printStackTrace();
		}
		try {
			registerDTO = userDAORemote.registerUser(registerDTO);
			facesContext.addMessage("registerForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Account created succesfully!", null));
			return "success";
		} catch (RegisterException e) {
			System.out.println("Invalid format.");
			facesContext.addMessage("registerForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			return null;
		}
	}


}