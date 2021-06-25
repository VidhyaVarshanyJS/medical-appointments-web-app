package com.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.example.dto.CenterDTO;
import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.exception.LoginException;
import com.example.util.DtoToEntity;
import com.example.util.EntityToDTO;

import model.Center;
import model.User;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless
@LocalBean
public class CenterDAO implements CenterDAORemote {

	static final Logger LOGGER = Logger.getLogger(CenterDAO.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	public CenterDAO() {

	}

	private EntityToDTO entityToDTO = new EntityToDTO();

	private DtoToEntity dtoToEntity = new DtoToEntity();

	@Override
	public List<CenterDTO> findAll() {
		List<Center> centers = entityManager.createNamedQuery("findAll", Center.class).getResultList();
		System.out.println(centers.toString());
		List<CenterDTO> dtoCenters = new ArrayList<>();
		for(Center center : centers) {
			dtoCenters.add(entityToDTO.convertCenter(center));
		}
		return dtoCenters;
	}
	
	@Override
	public CenterDTO findById(int id) {
		Center center = entityManager.find(Center.class, id);
		CenterDTO centerDTO = entityToDTO.convertCenter(center);
		return centerDTO;
	}

	public String findCenterByID(int id) {
		Center center = entityManager.find(Center.class, id);
		return center.getCenterName();
	}
	
	@Override
	public CenterDTO create(CenterDTO centerDTO) {
		Center center = dtoToEntity.convertCenter(centerDTO);
		entityManager.persist(center);
		entityManager.flush();
		centerDTO.setIdcenter(center.getIdcenter());
		return centerDTO;
	}

	@Override
	public CenterDTO update(CenterDTO centerDTO) {
		Center center = dtoToEntity.convertCenter(centerDTO);
		center.setIdcenter(centerDTO.getIdcenter());
		center = entityManager.merge(center);
		return centerDTO;
	}

	@Override
	public void delete(int id) {
		Center center = entityManager.find(Center.class, id);
		entityManager.remove(center);

	}


	
}

