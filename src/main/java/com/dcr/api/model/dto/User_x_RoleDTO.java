package com.dcr.api.model.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class User_x_RoleDTO {

    private String username;

    private String name;

    private String area;

    private String ativo;

    private String token;

    private List<RoleUserDTO> roles;

    public User_x_RoleDTO(String username, String name, String area, String ativo, String token,
            List<RoleUserDTO> roles) {
        this.username = username;
        this.name = name;
        this.area = area;
        this.ativo = ativo;
        this.token = token;
        this.roles = roles;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<RoleUserDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleUserDTO> roles) {
        this.roles = roles;
    }

}
