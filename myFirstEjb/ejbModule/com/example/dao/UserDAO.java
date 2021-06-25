package com.example.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.example.dto.UserDTO;
import com.example.exception.ChangePasswordException;
import com.example.exception.LoginException;
import com.example.exception.RegisterException;
import com.example.exception.UpdateProfileException;
import com.example.exception.UserException;
import com.example.util.DtoToEntity;
import com.example.util.EntityToDTO;

import model.User;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless
@LocalBean
public class UserDAO implements UserDAORemote {

	static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	public UserDAO() {

	}

	private EntityToDTO entityToDTO = new EntityToDTO();

	private DtoToEntity dtoToEntity = new DtoToEntity();

	@Override
	public UserDTO findById(int id) {
		User user = entityManager.find(User.class, id);
		UserDTO userDTO = entityToDTO.convertUser(user);
		return userDTO;
	}

	@Override
	public List<UserDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM User u");
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		System.out.println(users.toString());
		List<UserDTO> dtoUsers = new ArrayList<>();
		for (User user : users) {
			dtoUsers.add(entityToDTO.convertUser(user));
		}
		return dtoUsers;
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		User user = dtoToEntity.convertUser(userDTO);
		entityManager.persist(user);
		entityManager.flush();
		userDTO.setId(user.getIduser());
		return userDTO;
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		User user = dtoToEntity.convertUser(userDTO);
		user.setIduser(userDTO.getId());
		user = entityManager.merge(user);
		return userDTO;
	}

	@Override
	public void delete(int id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);

	}
	
	@Override
	public UserDTO loginUser(LoginDTO loginDTO) throws LoginException {
		User user = null;
		try {
			user = entityManager.createNamedQuery("findUserByUsername", User.class)
					.setParameter("username", loginDTO.getUsername()).getSingleResult();
		} catch (NoResultException e) {
			throw new LoginException("Wrong authentication!");
		}
		if (!loginDTO.getPassword().equals(user.getPassword())) {
			throw new LoginException("Wrong authentication!");
		}
		
		UserDTO userDTO = entityToDTO.convertUser(user);
		return userDTO;

	}
	
	@Override
	public RegisterDTO registerUser(RegisterDTO registerDTO) throws RegisterException {
		String tempGender = registerDTO.getGender();
		
		if(!tempGender.contains("m") && !tempGender.contains("f") && !tempGender.contains("ns")) {
			throw new RegisterException("You entered a invalid gender!");
		}
		
		if(!registerDTO.getIdentityCardNumber().chars().allMatch(Character::isDigit)) {
			throw new RegisterException("Identify card number must contain only numbers!");
		}
		
		
		User user = null;
		try {
			user = entityManager.createNamedQuery("findUserByUsername", User.class)
					.setParameter("username", registerDTO.getUsername()).getSingleResult();
		} catch (NoResultException e) {
			
			User userEntity = dtoToEntity.convertUserRegister(registerDTO);
			entityManager.persist(userEntity);
			entityManager.flush();
			registerDTO.setId(userEntity.getIduser());
			return registerDTO;
		}
			throw new RegisterException("The username already exist!");
			
	}
	
	@Override
	public UserDTO getUserProfile(String username) {
		User user = null;
		user = entityManager.createNamedQuery("findUserByUsername", User.class)
				.setParameter("username", username).getSingleResult();
		UserDTO userDTO = entityToDTO.convertUser(user);
		return userDTO;
	}
		
	@Override
	public Boolean updatePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException {
		User user = null;
		LOGGER.log(Level.INFO, "Trying to update password for:  " + changePasswordDTO.toString());
		try {
			user = entityManager.createNamedQuery("findUserByUsername", User.class)
					.setParameter("username", changePasswordDTO.getUsername()).getSingleResult();
			if (user.getPassword().equals(changePasswordDTO.getOldPassword())) {
				if (!changePasswordDTO.getOldPassword().equals(changePasswordDTO.getNewPassword())) {
					user.setPassword(changePasswordDTO.getNewPassword());
					user = entityManager.merge(user);
					LOGGER.log(Level.INFO, "Successfully changed password for:  " + changePasswordDTO.toString());
					return true;
				} else {
					throw new ChangePasswordException(
							"Please choose another new password, not the same as the old one!");
				}
			} else
				throw new ChangePasswordException("The old password is not valid.");
		} catch (NoResultException e) {
			throw new ChangePasswordException("The username is not valid!");
		}

	}
	
	@Override
	public Boolean editUserProfile(UserDTO userDTO) throws UpdateProfileException {
		User user = null;
		LOGGER.log(Level.INFO, "Trying to update profile for:  " + userDTO.toString());
		try {
			user = entityManager.createNamedQuery("findUserByUsername", User.class)
					.setParameter("username", userDTO.getUsername()).getSingleResult();
					
					if(user.getFirstName() != userDTO.getFirstName())
						user.setFirstName(userDTO.getFirstName());
					
					if(user.getLastName() != userDTO.getLastName())
						user.setLastName(userDTO.getLastName());
					
					if(user.getDateOfBirth() != userDTO.getDateOfBirth())
						user.setDateOfBirth(userDTO.getDateOfBirth());
					
					if(user.getGender() != userDTO.getGender())
						user.setGender(userDTO.getGender());
					
					if(user.getIdentitycardCnp() != userDTO.getIdentityCardNumber())
						user.setIdentitycardCnp(userDTO.getIdentityCardNumber());
						
					user = entityManager.merge(user);
					LOGGER.log(Level.INFO, "Successfully updated profile for:  " + userDTO.toString());
					return true;
			} catch (NoResultException e) {
				throw new UpdateProfileException("This username is not valid!");
			}

	}

}
