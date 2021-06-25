package managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import com.example.dao.CenterDAORemote;
import com.example.dao.UserDAORemote;
import com.example.dto.CenterDTO;

@Named(value = "centerBean")
@ManagedBean
@RequestScoped
public class CenterBean {
	@EJB
	CenterDAORemote centerDAORemote;
	
	private List<CenterDTO> centers;

	public List<CenterDTO> getCenters() {
		centers = centerDAORemote.findAll();
		return centers;
	}
}

