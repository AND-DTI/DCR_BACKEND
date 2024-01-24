package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dcr.api.model.as400.User_Role;

public interface UserRoleRepository extends JpaRepository<User_Role, Integer> {

	@Modifying
	@Query(value=
		" select a.username as username, a.roleid as roleid, b.rolename as rolename, "+
		"        b.roledesc as roledesc" + 
		" from   ptdhd.CTPUSERRL as a join " +
		"        ptdhd.CTPROLE as b on a.roleid = b.roleid  "+
		" where  a.username = :usr", 
	       nativeQuery=true)   
	List<User_Role> findRole_UserDTOs(@Param("usr") String username);
	
}
