package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dcr.api.model.as400.Accoper;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.model.keys.User_RoleKey;

public interface UserRoleRepository extends JpaRepository<User_Role, User_RoleKey> {

	  @Query(value = "SELECT * FROM HD4DCDHH.ACCUSERRL AS RL WHERE RL.USERNAME = :username", nativeQuery = true)
	   List<User_Role> findByUsername(String username);
	
	   @Query(value = "SELECT * FROM HD4DCDHH.ACCUSERRL AS RL WHERE RL.ROLENAME = :rolename", nativeQuery = true)
	   List<User_Role> findByRolename(String rolename);
}
