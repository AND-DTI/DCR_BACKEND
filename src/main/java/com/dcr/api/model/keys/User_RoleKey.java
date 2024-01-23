package com.dcr.api.model.keys;

import java.io.Serializable;

public class User_RoleKey implements Serializable {

    private String rolename;
    private Integer roleid;

    public User_RoleKey() {

    }

    public User_RoleKey(String rolename, Integer roleid) {
        this.rolename = rolename;
        this.roleid = roleid;
    }


    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rolename == null) ? 0 : rolename.hashCode());
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
        if (rolename == null) {
            if (other.rolename != null)
                return false;
        } else if (!rolename.equals(other.rolename))
            return false;
        if (roleid == null) {
            if (other.roleid != null)
                return false;
        } else if (!roleid.equals(other.roleid))
            return false;
        return true;
    }

}