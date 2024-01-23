package com.dcr.api.model.as400;

import static com.dcr.api.utils.Auxiliar.*;

import org.springframework.security.core.GrantedAuthority;

import com.dcr.api.model.keys.User_RoleKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "ACCROLES", schema = "HD4DCDHH")
@IdClass(User_RoleKey.class)
public class User_Role implements GrantedAuthority {

    @Id
    @Column(columnDefinition = "int", unique = true)
    private Integer roleid;

    @Column(columnDefinition = "char(20)")
    private String rolename;


    public User_Role() {

    }

    public User_Role(Integer roleid, String rolename) {
        super();
        this.roleid = roleid;
        this.rolename = rolename;
    }


    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public void setRolname(String rolename) {
        this.rolename = rolename;
    }


    @Override
    public String getAuthority() {
        return trimNull(this.rolename);
    }

}