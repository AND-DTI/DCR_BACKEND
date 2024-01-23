package com.dcr.api.model.dto;

import static com.dcr.api.utils.Auxiliar.*;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class RoleUserDTO implements Serializable {

    @JsonIgnore

    private String username;

    private Integer roleidDTO;

    private String rolename;

    private String roledesc;

    private String rolecad;

    public RoleUserDTO(String username, Integer roleidDTO, String rolename, String roledesc, String rolecad) {
        this.username = username;
        this.roleidDTO = roleidDTO;
        this.rolename = rolename;
        this.roledesc = roledesc;
        this.rolecad = rolecad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleidDTO() {
        return roleidDTO;
    }

    public void setRoleidDTO(Integer roleidDTO) {
        this.roleidDTO = roleidDTO;
    }

    public String getRolename() {
        return trimNull(rolename);
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return trimNull(roledesc);
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public String getRolecad() {
        return rolecad;
    }

    public void setRolecad(String rolecad) {
        this.rolecad = rolecad;
    }

}