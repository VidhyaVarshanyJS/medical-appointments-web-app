package com.example.dao;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import com.example.dto.CenterDTO;
import com.example.dto.ChangePasswordDTO;
import com.example.dto.LoginDTO;
import com.example.dto.RegisterDTO;
import com.example.dto.UserDTO;
import com.example.exception.ChangePasswordException;
import com.example.exception.LoginException;
import com.example.exception.RegisterException;
import com.example.exception.UpdateProfileException;
import com.example.exception.UserException;

@Remote
public interface CenterDAORemote extends GenericDAO<CenterDTO> {
	
	List<CenterDTO> findAll();
	
	String findCenterByID(int id);
}
