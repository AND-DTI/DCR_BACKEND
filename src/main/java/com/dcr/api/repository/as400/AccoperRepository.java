package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Accoper;
import com.dcr.api.model.keys.AccoperKey;

public interface AccoperRepository extends JpaRepository<Accoper, AccoperKey>{

	   @Query(value = "SELECT a.* FROM HD4DCDHH.ACCOPER as a WHERE a.cdmodule = :cdmodule AND a.ativo = 'S'", nativeQuery = true)
	   List<Accoper> findByCdmoduleAndAtivo(String cdmodule);
	  
}
