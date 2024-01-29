package com.dcr.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class RoleDTO {

    private long roleid;

    private String rolename;

    private String roledesc;

    public RoleDTO(long roleid, String rolename, String roledesc) {
        this.roleid = roleid;
        this.rolename = rolename;
        this.roledesc = roledesc;
    }

    public RoleDTO() {

    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

}