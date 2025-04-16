package com.dcr.api.repository.as400;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Accroles;


public interface RoleRepository extends JpaRepository<Accroles, Integer> {

	  @Query(value = "SELECT * FROM HD4DCDHH.ACCROLES AS dcr WHERE dcr.rolename = :rolename", nativeQuery = true)
	  Optional<Accroles> consultaByRoleName(String rolename);

}
