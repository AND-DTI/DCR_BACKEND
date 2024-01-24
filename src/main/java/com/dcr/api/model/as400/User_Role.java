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
@Table(name = "ACCUSERRL", schema = "HD4DCDHH", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "roleid" }) })
@IdClass(User_RoleKey.class)
public class User_Role implements GrantedAuthority {

	@Id
    @Column(columnDefinition = "char(10)", unique = true)
    private String username;

    @Id
    @Column(columnDefinition = "int", unique = true)
    private Integer roleid;

    @Column(columnDefinition = "char(20)")
    private String rolename;

    public User_Role() {

    }

    public User_Role(String username, Integer roleid, String rolename, String dtacad) {
        super();
        this.username = username;
        this.roleid = roleid;
        this.rolename = rolename;
    }

    public String getUsername() {        return trimNull(username);    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleid() {        return roleid;    }
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    //public String getRolname() {        return rolename;    }
    public void setRolname(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String getAuthority() {        
        return trimNull(this.rolename);
    }

}