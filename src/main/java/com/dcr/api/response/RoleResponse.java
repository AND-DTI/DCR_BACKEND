package com.dcr.api.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class RoleResponse {
	private String roleName;
	private String roleDesc;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
