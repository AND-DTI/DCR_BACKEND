package com.dcr.api.model.keys;

import java.io.Serializable;

import com.dcr.api.validator.TamanhoMaximo;

public class User_RoleKey implements Serializable{

	@TamanhoMaximo(10)
    private String username;	
	
	@TamanhoMaximo(9)
	private Integer roleid;
	
	

    public User_RoleKey(){

    }

    public User_RoleKey(String username, Integer roleid ) { 
        this.username = username;
        this.roleid = roleid;        
    }

    public String getUsername() {        return username;    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleid() {        return roleid;    }
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((roleid == null) ? 0 : roleid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User_RoleKey other = (User_RoleKey) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (roleid == null) {
            if (other.roleid != null)
                return false;
        } else if (!roleid.equals(other.roleid))
            return false;
        return true;
    }


}