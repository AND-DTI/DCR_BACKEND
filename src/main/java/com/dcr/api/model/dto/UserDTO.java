package com.dcr.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class UserDTO {

    private String username;

    private String name;

    private String rolename;

    public UserDTO(String username, String name, String rolename) {
        this.username = username;
        this.name = name;
        this.rolename = rolename;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
