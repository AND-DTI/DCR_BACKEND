package com.dcr.api.response;
import java.util.List;
import io.swagger.annotations.ApiModel;




@ApiModel
public class LoginResponse {
	
	private String username;
	private String token;
	private String name;
	private String idArea;
	private List<RoleResponse> roles;
	
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
	public List<RoleResponse> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleResponse> roles) {
		this.roles = roles;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdArea() {
		return idArea;
	}
	public void setIdArea(String idArea) {
		this.idArea = idArea;
	}
}
