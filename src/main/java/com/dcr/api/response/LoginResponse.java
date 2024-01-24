package com.dcr.api.response;

import java.util.List;

import com.dcr.api.model.as400.User_Role;

public class LoginResponse {
	private String username;
	private String token;
	private List<User_Role> roles;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<User_Role> getRoles() {
		return roles;
	}
	public void setRoles(List<User_Role> roles) {
		this.roles = roles;
	}
}
