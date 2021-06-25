package com.example.dto;

public class LoginDTO {

	String username;
	String password;
	int role;
	

	public LoginDTO() {
		super();
	}

	public LoginDTO(String username, String password, int role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
