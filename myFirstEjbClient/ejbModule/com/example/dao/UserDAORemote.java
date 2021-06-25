package com.example.dao;

import java.text.ParseException;

import javax.ejb.Remote;

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
public interface UserDAORemote extends GenericDAO<UserDTO> {

	UserDTO loginUser(LoginDTO loginDTO) throws LoginException;

	RegisterDTO registerUser(RegisterDTO registerDTO) throws RegisterException;
	
	Boolean updatePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException;

	UserDTO getUserProfile(String username);

	Boolean editUserProfile(UserDTO userDTO) throws UpdateProfileException;
}
